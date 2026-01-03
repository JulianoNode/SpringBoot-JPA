package com.strart.web.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String dashboard(Model model) {
		model.addAttribute("limiteStart", service.limiteAtingido(TipoBory.START));
		model.addAttribute("limiteMiddle", service.limiteAtingido(TipoBory.MIDDLE));
		model.addAttribute("limiteEnd", service.limiteAtingido(TipoBory.END));
		model.addAttribute("limiteCertificado", service.limiteAtingido(TipoBory.CERTIFICADO));
		return "bory/dashboard";
	}

	@GetMapping("/lista/{tipo}")
	public String listarPorTipo(@PathVariable TipoBory tipo, Model model) {
		List<Bory> bories = repository.findByTipo(tipo);

		model.addAttribute("bories", bories);
		model.addAttribute("tipo", tipo);
		model.addAttribute("limiteAtingido", service.limiteAtingido(tipo));

		return "bory/lista";
	}

	// FORMULÁRIO
	@GetMapping("/novo/{tipo}")
	public String novo(@PathVariable String tipo, Model model) {
	    TipoBory tipoEnum = TipoBory.valueOf(tipo.toUpperCase());
	    Bory bory = new Bory();
	    bory.setTipo(tipoEnum);
	    model.addAttribute("bory", bory);
	    return "bory/form";
	}

	// SALVAR
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Bory bory, @RequestParam(value = "file", required = false) MultipartFile file,
			RedirectAttributes redirect) {

		try {
			service.salvar(bory, file);
			redirect.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
		} catch (IllegalStateException e) {
			redirect.addFlashAttribute("erro", e.getMessage());
		} catch (IOException e) {
			redirect.addFlashAttribute("erro", "Erro ao salvar a imagem.");
		}

		return "redirect:/bory";
	}

}
