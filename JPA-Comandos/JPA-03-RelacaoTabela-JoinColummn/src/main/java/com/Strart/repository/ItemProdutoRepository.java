package com.Strart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Strart.model.ItemProduto;

public interface ItemProdutoRepository extends JpaRepository<ItemProduto, Long> {
}