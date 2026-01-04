package com.strart.service;

import org.springframework.stereotype.Service;

@Service
public class ImagemService {

   /* private static final String DIRETORIO_BASE = "uploads";

    public String salvarImagem(MultipartFile arquivo, String subpasta) throws IOException {

        if (arquivo == null || arquivo.isEmpty()) {
            return null;
        }

        if (!arquivo.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Arquivo não é uma imagem");
        }

        String nomeArquivo = UUID.randomUUID() + "_" +
                arquivo.getOriginalFilename().replaceAll("\\s+", "_");

        Path pasta = Paths.get(DIRETORIO_BASE, subpasta);
        Files.createDirectories(pasta);

        Files.write(pasta.resolve(nomeArquivo), arquivo.getBytes());

        return nomeArquivo;
    }

    public ResponseEntity<byte[]> carregarImagem(String nome, String subpasta) throws IOException {

        Path caminho = Paths.get(DIRETORIO_BASE, subpasta).resolve(nome);

        byte[] bytes = Files.readAllBytes(caminho);
        String contentType = Files.probeContentType(caminho);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(bytes);
                
    }*/
}
