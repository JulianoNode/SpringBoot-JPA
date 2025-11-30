package com.JPA_Start.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.JPA_Start.model.Produto;
import com.JPA_Start.service.ProdutoService;

@Controller
@RequestMapping({ "/", "/produtos" })
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	/*
	 * @GetMapping public String listar(Model model) {
	 * model.addAttribute("produtos", service.listarTodos()); return
	 * "produtos/list"; // templates/produtos/list.html }
	 */
	@GetMapping
	public String listar(@RequestParam(value = "busca", required = false) String busca,
			@RequestParam(value = "min", required = false) Double min,
			@RequestParam(value = "max", required = false) Double max,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "dir", defaultValue = "asc") String dir,
			@RequestParam(value = "page", defaultValue = "0") int page, Model model) {

		Page<Produto> produtos = service.buscaAvancada(busca, min, max, sort, dir, page);

		model.addAttribute("produtos", produtos);
		model.addAttribute("busca", busca);
		model.addAttribute("min", min);
		model.addAttribute("max", max);
		model.addAttribute("sort", sort);
		model.addAttribute("dir", dir);

		return "produtos/list";
	}

	@GetMapping("/novo")
	public String novoForm(Model model) {
		model.addAttribute("produto", new Produto());
		return "produtos/form"; // templates/produtos/form.html
	}

	@PostMapping("/salvar")
	public String salvar(@ModelAttribute Produto produto) {
		if (produto.getId() != null) {
			// editar
			service.atualizar(produto.getId(), produto);
		} else {
			// novo produto
			service.salvar(produto);
		}
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