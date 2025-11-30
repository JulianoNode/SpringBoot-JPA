package com.JPA_Start.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JPA_Start.model.Categoria;
import com.JPA_Start.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("categorias", categoriaService.findAll());
		return "categorias/list";
	}

	@GetMapping("/novo")
	public String novaCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "categorias/form";
	}

	@PostMapping
	public String salvar(@ModelAttribute Categoria categoria) {
		categoriaService.save(categoria);
		return "redirect:/categorias";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute("categoria", categoriaService.findById(id));
		return "categorias/form";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		categoriaService.delete(id);
		return "redirect:/categorias";
	}
}