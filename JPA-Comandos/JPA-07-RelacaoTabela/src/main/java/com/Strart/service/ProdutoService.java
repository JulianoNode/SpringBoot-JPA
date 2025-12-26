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
    
    public Produto salvar(ProdutoDTO dto) {
        Produto produto = modelMapper.map(dto, Produto.class);
       
        if (dto.getFornecedorIds() != null) {
            produto.setFornecedores(
                fornecedorRepository.findAllById(dto.getFornecedorIds())
            );
        }
        if (dto.getCategoriaIds() != null) {
            produto.setCategorias(
                categoriaRepository.findAllById(dto.getCategoriaIds())
            );
        }
        return produtoRepository.save(produto);
    }
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}