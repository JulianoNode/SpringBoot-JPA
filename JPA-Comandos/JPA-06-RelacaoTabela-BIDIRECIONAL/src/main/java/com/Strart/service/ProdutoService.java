package com.Strart.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Strart.DTOs.ProdutoDTO;
import com.Strart.model.Produto;
import com.Strart.repository.CategoriaRepository;
import com.Strart.repository.FornecedorRepository;
import com.Strart.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private FornecedorRepository fornecedorRepository;
	@Autowired
	private ModelMapper mapper;

	// Listar Produto
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	// Salvar Produto
	public void salvar(ProdutoDTO dto) {
		Produto produto = mapper.map(dto, Produto.class);

		produto.setImagem(dto.getImagem());

		produto.setCategoria(categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada")));

		produto.setFornecedor(fornecedorRepository.findById(dto.getFornecedorId())
				.orElseThrow(() -> new RuntimeException("Fornecedor não encontrado")));

		produtoRepository.save(produto);
	}

	// Editar produto
	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}

	// Editar produto para atualizar
	public void atualizar(ProdutoDTO dto) {
		Produto produto = buscarPorId(dto.getId());
		produto.setNome(dto.getNome());
		produto.setPreco(dto.getPreco());
		produto.setImagem(dto.getImagem()); // 🔥 ESSENCIAL

		produtoRepository.save(produto);
	}

	// Excluir produto
	public void excluir(Long id) {
		produtoRepository.deleteById(id);
	}
}