package com.Strart.service;

import java.util.List;

import com.Strart.model.ItemProduto;
import com.Strart.model.Produto;

public interface ProdutoService  {
	List<Produto> listarTodos();
	Produto buscarPorId(Long id);
	Produto salvar(Produto produto);
	void deletar(Long id);
	ItemProduto buscarItemPorId(Long id);

	Long deletarItem(Long id);
}
