package com.Strart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Strart.model.ItemProduto;
import com.Strart.model.Produto;
import com.Strart.repository.ItemProdutoRepository;
import com.Strart.repository.ProdutoRepository;
import com.Strart.service.ItemProdutoService;

@Service
public class ItemProdutoServiceImpl implements ItemProdutoService {

	private final ItemProdutoRepository itemRepo;
	private final ProdutoRepository produtoRepo;

	public ItemProdutoServiceImpl(ItemProdutoRepository itemRepo, ProdutoRepository produtoRepo) {
		this.itemRepo = itemRepo;
		this.produtoRepo = produtoRepo;
	}
	@Override
	public List<ItemProduto> listarPorProduto(Long produtoId) {
		Produto p = produtoRepo.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		return p.getItens();
	}
	@Override
	public ItemProduto buscarPorId(Long id) {
		return itemRepo.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
	}
	@Override
	public ItemProduto salvar(ItemProduto item) {
		return itemRepo.save(item);
	}
	@Override
	public Long deletar(Long id) {
		ItemProduto item = buscarPorId(id);
		Long produtoId = item.getProduto().getId();

		itemRepo.delete(item);
		return produtoId;
	}
}