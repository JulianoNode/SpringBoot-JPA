package com.JPA_Start.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JPA_Start.model.Produto;
import com.JPA_Start.service.ProdutoService;

@Controller
@RequestMapping({"/","/produtos"})
public class ProdutoController {

	private final ProdutoService service;

	public ProdutoController(ProdutoService service) {
		this.service = service;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("produtos", service.listarTodos());
		return "produtos/list"; // templates/produtos/list.html
	}

	@GetMapping("/novo")
	public String novoForm(Model model) {
		model.addAttribute("produto", new Produto());
		return "produtos/form"; // templates/produtos/form.html
	}

	@PostMapping
	public String salvar(@ModelAttribute Produto produto) {
		service.salvar(produto);
		return "redirect:/produtos";
	}

	@GetMapping("/editar/{id}")
	public String editarForm(@PathVariable Long id, Model model) {
		Produto p = service.buscarPorId(id);
		if (p == null) {
			p = new Produto();
		}
		model.addAttribute("produto", p);
		return "produtos/form";
	}

	@GetMapping("/deletar/{id}")
	public String deletar(@PathVariable Long id) {
		service.deletar(id);
		return "redirect:/produtos";
	}
}