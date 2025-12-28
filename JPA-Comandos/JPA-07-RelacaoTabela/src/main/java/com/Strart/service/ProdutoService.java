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
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ModelMapper modelMapper;

	public Produto salvar(ProdutoDTO produtoDTO) {
		Produto produto = modelMapper.map(produtoDTO, Produto.class);

		if (produtoDTO.getFornecedorIds() != null) {
			produto.setFornecedores(fornecedorRepository.findAllById(produtoDTO.getFornecedorIds()));
		}
		if (produtoDTO.getCategoriaIds() != null) {
			produto.setCategorias(categoriaRepository.findAllById(produtoDTO.getCategoriaIds()));
		}
		return produtoRepository.save(produto);
	}

	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	// Buscar por Id
	public Produto buscarPorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}

	// Editar Produto
	public Produto editar(ProdutoDTO dto) {
		Produto produto = produtoRepository.findById(dto.getId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		produto.setNome(dto.getNome());
		produto.setFornecedores(fornecedorRepository.findAllById(dto.getFornecedorIds()));
		produto.setCategorias(categoriaRepository.findAllById(dto.getCategoriaIds()));
		return produtoRepository.save(produto);
	}

	// Excluir por Id
	public void excluir(long id) {
		produtoRepository.deleteById(id);
	}

}