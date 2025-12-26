package com.Strart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Strart.model.StartModel;

public interface StartModelRepository extends JpaRepository<StartModel, Long>{
	List<StartModel> findByProdutoId(Long produtoId);
}
