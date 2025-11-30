package com.Strart.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Strart.model.ItemProduto;
import com.Strart.model.Produto;
import com.Strart.model.dto.ItemProdutoDTO;
import com.Strart.service.ItemProdutoService;
import com.Strart.service.ProdutoService;

@Controller
@RequestMapping("/itens")
public class ItemProdutoController {

	private final ProdutoService produtoService;
	private final ItemProdutoService itemProdutoService;
	private final ModelMapper mapper;

	public ItemProdutoController(ProdutoService produtoService, ItemProdutoService itemProdutoService,
			ModelMapper mapper) {
		this.produtoService = produtoService;
		this.itemProdutoService = itemProdutoService;
		this.mapper = mapper;
	}

	@GetMapping("/produto/{produtoId}")
	public String listar(@PathVariable Long produtoId, Model model) {
		Produto produto = produtoService.buscarPorId(produtoId);
		model.addAttribute("produto", produto);
		model.addAttribute("itens", produto.getItens());
		return "itens/list";
	}

	@GetMapping("/produto/{produtoId}/novo")
	public String novo(@PathVariable Long produtoId, Model model) {
		ItemProdutoDTO dto = new ItemProdutoDTO();
		dto.setProdutoId(produtoId);
		model.addAttribute("item", dto);
		model.addAttribute("produtoId", produtoId);
		return "itens/form";
	}

	@PostMapping("/salvar")
	public String salvar(@ModelAttribute("item") ItemProdutoDTO dto) {
		ItemProduto item = mapper.map(dto, ItemProduto.class);
		Produto produto = produtoService.buscarPorId(dto.getProdutoId());
		item.setProduto(produto);
		itemProdutoService.salvar(item);
		return "redirect:/itens/produto/" + produto.getId();
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Long id, Model model) {
		ItemProduto item = itemProdutoService.buscarPorId(id);
		ItemProdutoDTO dto = mapper.map(item, ItemProdutoDTO.class);
		dto.setProdutoId(item.getProduto().getId());
		model.addAttribute("item", dto);
		model.addAttribute("produtoId", dto.getProdutoId());
		return "itens/form";
	}

	@GetMapping("/deletar/{id}")
	public String deletar(@PathVariable Long id) {
		Long produtoId = itemProdutoService.deletar(id);
		return "redirect:/itens/produto/" + produtoId;
	}
}