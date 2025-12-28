package com.Strart.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Strart.DTOs.StartModelDTO;
import com.Strart.model.Produto;
import com.Strart.model.StartModel;
import com.Strart.repository.ProdutoRepository;
import com.Strart.repository.StartModelRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StartModelService {

	@Autowired
	private StartModelRepository startModelRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper modelMapper;

	// 🔹 SALVAR
	public StartModel salvar(StartModelDTO dto) {
		StartModel startModel = modelMapper.map(dto, StartModel.class);
		Produto produto = produtoRepository.findById(dto.getProdutoId())
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		startModel.setProduto(produto);
		return startModelRepository.save(startModel);
	}

	// 🔹 Listar
	public List<StartModel> listar() {
		return startModelRepository.findAll();
	}

	// 🔹 Listar Por Produto
	public List<StartModel> listarPorProduto(Long produtoId) {
		return startModelRepository.findByProdutoId(produtoId);
	}

	// 🔹 BUSCAR POR ID
	public StartModel buscarPorId(Long id) {
		return startModelRepository.findById(id).orElseThrow(() -> new RuntimeException("StartModel não encontrado"));
	}

	// 🔹 EDITAR
	public StartModel editar(StartModelDTO dto) {

		StartModel sm = startModelRepository.findById(dto.getId())
				.orElseThrow(() -> new RuntimeException("StartModel não encontrado"));

		sm.setTitulo(dto.getTitulo());
		sm.setDescricao(dto.getDescricao());
		sm.setImagem(dto.getImagem());

		return startModelRepository.save(sm);
	}

	// 🔹 EXCLUIR
	public void excluir(Long id) {
		startModelRepository.deleteById(id);
	}

}