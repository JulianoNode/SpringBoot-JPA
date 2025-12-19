package com.Strart.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/imagens")
public class ImagemController {

	@GetMapping("/{nome}")
	@ResponseBody
	public ResponseEntity<byte[]> carregarImagem(@PathVariable String nome) throws IOException {
	    Path caminho = Paths.get("uploads").resolve(nome);

	    byte[] imagem = Files.readAllBytes(caminho);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(caminho))
	        .body(imagem);
	}
}
