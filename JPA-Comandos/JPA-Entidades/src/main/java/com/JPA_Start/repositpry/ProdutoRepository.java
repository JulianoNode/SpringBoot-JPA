package com.JPA_Start.repositpry;

import org.springframework.data.jpa.repository.JpaRepository;
import com.JPA_Start.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
