package com.Strart.service;

import java.util.List;

import com.Strart.model.ItemProduto;

public interface ItemProdutoService {
	List<ItemProduto> listarPorProduto(Long produtoId);
	ItemProduto buscarPorId(Long id);
	ItemProduto salvar(ItemProduto item);
	Long deletar(Long id);
}