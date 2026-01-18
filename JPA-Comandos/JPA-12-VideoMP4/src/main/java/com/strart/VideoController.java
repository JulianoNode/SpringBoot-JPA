package com.strart;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/videos")
public class VideoController {

	private final VideoService service;
	private final VideoRepository repository;

	public VideoController(VideoService service, VideoRepository repository) {
		this.service = service;
		this.repository = repository;
	}

	// 🔹 PÁGINA DO PLAYER
	@GetMapping
	public String listar(Model model) {
		model.addAttribute("videos", repository.findAll());
		return "videos-player";
	}

	// 🔹 PÁGINA DE UPLOAD
	@GetMapping("/upload")
	public String upload() {
		return "videos-upload";
	}

	// 🔹 SALVAR VÍDEO
	@PostMapping("/salvar")
	public String salvar(@RequestParam("file") MultipartFile file) throws IOException {
		service.salvar(file, "videos/aulas");
		return "redirect:/videos";
	}
}
