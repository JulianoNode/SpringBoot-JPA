package com.strart;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/stream")
public class VideoStreamController {

    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB

    @GetMapping("/**")
    public ResponseEntity<Resource> stream(
            @RequestHeader(value = "Range", required = false) String rangeHeader)
            throws IOException {

        String uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .getPath();

        String arquivo = uri.replace("/stream/", "");
        Path path = Paths.get("upload").resolve(arquivo);

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        long tamanho = Files.size(path);
        String mime = Files.probeContentType(path);

        long inicio = 0;
        long fim = tamanho - 1;

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            String[] ranges = rangeHeader.replace("bytes=", "").split("-");
            inicio = Long.parseLong(ranges[0]);
            fim = Math.min(inicio + CHUNK_SIZE - 1, tamanho - 1);
        }

        long contentLength = fim - inicio + 1;

        InputStream inputStream = Files.newInputStream(path);
        inputStream.skip(inicio);

        InputStreamResource resource = new InputStreamResource(inputStream);

        return ResponseEntity
                .status(rangeHeader == null ? HttpStatus.OK : HttpStatus.PARTIAL_CONTENT)
                .header(HttpHeaders.CONTENT_TYPE, mime)
                .header(HttpHeaders.ACCEPT_RANGES, "bytes")
                .header(HttpHeaders.CONTENT_RANGE,
                        "bytes " + inicio + "-" + fim + "/" + tamanho)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength))
                .body(resource);
    }
}
