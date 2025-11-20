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
import com.JPA_Start.model.Produto;
import com.JPA_Start.service.CategoriaService;
import com.JPA_Start.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("produtos", produtoService.findAll());
		return "produtos/list";
	}

	@GetMapping("/novo")
	public String novoProduto(Model model) {
		model.addAttribute("produto", new Produto());
		model.addAttribute("categorias", categoriaService.findAll());
		return "produtos/form";
	}

	@PostMapping
	public String salvar(@ModelAttribute Produto produto) {

		if (produto.getCategoria() != null && produto.getCategoria().getId() != null) {
			Categoria categoria = categoriaService.findById(produto.getCategoria().getId());
			produto.setCategoria(categoria);
		}

		produtoService.save(produto);
		return "redirect:/produtos";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		model.addAttribute("produto", produtoService.findById(id));
		model.addAttribute("categorias", categoriaService.findAll());
		return "produtos/form";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		produtoService.delete(id);
		return "redirect:/produtos";
	}
}