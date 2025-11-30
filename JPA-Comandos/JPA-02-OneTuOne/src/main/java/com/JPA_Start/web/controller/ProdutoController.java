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
@RequestMapping({ "/", "/produtos" })
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
	public String salvar(@ModelAttribute Produto produto, Model model) {
		try {
			// Trata o relacionamento @OneToOne / @ManyToOne corretamente
			if (produto.getCategoria() != null && produto.getCategoria().getId() != null) {
				Categoria categoria = categoriaService.findById(produto.getCategoria().getId());
				if (categoria == null) {
					model.addAttribute("erro", "Categoria selecionada não existe.");
					model.addAttribute("categorias", categoriaService.findAll());
					return "produtos/form";
				}
				produto.setCategoria(categoria);
			}
			produtoService.save(produto);
			return "redirect:/produtos";
		} catch (Exception e) {
			// Log para desenvolvedor (opcional)
			e.printStackTrace();
			// Mensagem para a view
			model.addAttribute("erro", "Ocorreu um erro ao salvar o produto: "
					+ "CATEGORIA JÁ USADA em outro Pruduto, "
					+ "Cadastre uma nova ' CATEGORIA para um NOVO PRODUTO  '");			
			// Sempre precisa recarregar categorias
			model.addAttribute("categorias", categoriaService.findAll());
			return "produtos/form";
		}
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