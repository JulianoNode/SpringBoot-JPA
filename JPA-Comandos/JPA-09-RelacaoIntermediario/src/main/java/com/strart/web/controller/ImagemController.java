package com.strart.web.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strart.service.ImagemService;

@RestController
@RequestMapping("/imagens")
public class ImagemController {

	private final ImagemService imagemService;

	public ImagemController(ImagemService imagemService) {
		this.imagemService = imagemService;
	}

	@GetMapping("/{pasta}/{nome}")
	public ResponseEntity<byte[]> carregar(@PathVariable String pasta, @PathVariable String nome) throws IOException {

		return imagemService.carregarImagem(nome, pasta);
	}
}
