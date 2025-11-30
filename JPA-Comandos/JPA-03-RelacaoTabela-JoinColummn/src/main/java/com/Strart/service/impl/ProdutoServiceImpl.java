package com.Strart.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.Strart.model.ItemProduto;
import com.Strart.model.Produto;
import com.Strart.repository.ItemProdutoRepository;
import com.Strart.repository.ProdutoRepository;
import com.Strart.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ItemProdutoRepository itemProdutoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository,
                              ItemProdutoRepository itemProdutoRepository) {
        this.produtoRepository = produtoRepository;
        this.itemProdutoRepository = itemProdutoRepository;
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public ItemProduto buscarItemPorId(Long id) {
        return itemProdutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item do produto não encontrado"));
    }

    @Override
    public Long deletarItem(Long id) {
        ItemProduto item = buscarItemPorId(id);
        Long produtoId = item.getProduto().getId();
        itemProdutoRepository.delete(item);
        return produtoId;
    }
}