package com.Strart.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Strart.model.Categoria;
import com.Strart.model.dto.CategoriaDTO;
import com.Strart.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	private final CategoriaService categoriaService;
	private final ModelMapper mapper;

	public CategoriaController(CategoriaService categoriaService, ModelMapper mapper) {
		this.categoriaService = categoriaService;
		this.mapper = mapper;
	}

	// LISTAR
	@GetMapping
	public String listar(Model model) {
		List<Categoria> categorias = categoriaService.listarTodas();
		List<CategoriaDTO> dtos = categorias.stream().map(c -> mapper.map(c, CategoriaDTO.class))
				.collect(Collectors.toList());
		model.addAttribute("categorias", dtos);
		return "categorias/list";
	}

	// FORM NOVA
	@GetMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("categoria", new CategoriaDTO());
		return "categorias/form";
	}

	// SALVAR (POST)
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute("categoria") CategoriaDTO dto) {
		Categoria entity = mapper.map(dto, Categoria.class);
		categoriaService.salvar(entity);
		return "redirect:/categorias";
	}

	// EDITAR
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Categoria categoria = categoriaService.buscarPorId(id);
		CategoriaDTO dto = mapper.map(categoria, CategoriaDTO.class);
		model.addAttribute("categoria", dto);
		return "categorias/form";
	}

	// DELETAR
	@GetMapping("/deletar/{id}")
	public String deletar(@PathVariable Long id) {
		categoriaService.deletar(id);
		return "redirect:/categorias";
	}
}