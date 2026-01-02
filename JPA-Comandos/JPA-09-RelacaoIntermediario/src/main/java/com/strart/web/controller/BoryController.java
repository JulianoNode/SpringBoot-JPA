package com.strart.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.strart.model.Bory;
import com.strart.model.TipoBory;
import com.strart.repository.BoryRepository;
import com.strart.service.BoryService;

@Controller
@RequestMapping("/bory")
public class BoryController {

	@Autowired
	private BoryRepository repository;

	@Autowired
	private BoryService service;

	// LISTAR
	@GetMapping
	public String dashboard() {
		return "bory/dashboard";
	}

	@GetMapping("/lista/{tipo}")
	public String listarPorTipo(@PathVariable TipoBory tipo, Model model) {
		model.addAttribute("tipo", tipo);
		model.addAttribute("bories", repository.findByTipo(tipo));
		return "bory/lista";
	}

	// FORMULÁRIO
	@GetMapping("/novo/start")
	public String novoStart(Model model) {
		Bory bory = new Bory();
		bory.setTipo(TipoBory.START);
		model.addAttribute("bory", bory);
		return "bory/form";
	}

	@GetMapping("/novo/middle")
	public String novoMiddle(Model model) {
		Bory bory = new Bory();
		bory.setTipo(TipoBory.MIDDLE);
		model.addAttribute("bory", bory);
		return "bory/form";
	}

	@GetMapping("/novo/end")
	public String novoEnd(Model model) {
		Bory bory = new Bory();
		bory.setTipo(TipoBory.END);
		model.addAttribute("bory", bory);
		return "bory/form";
	}

	@GetMapping("/novo/certificado")
	public String novoCertificado(Model model) {
		Bory bory = new Bory();
		bory.setTipo(TipoBory.CERTIFICADO);
		model.addAttribute("bory", bory);
		return "bory/form";
	}

	// SALVAR
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Bory bory, @RequestParam(value = "file", required = false) MultipartFile file)
			throws IOException {

		service.salvar(bory, file);
		return "redirect:/bory";
	}
}
