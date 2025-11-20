package com.JPA_Start.repositpry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.JPA_Start.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

	@Query("""
			    SELECT p FROM Produto p
			    WHERE (:nome IS NULL OR :nome = '' OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
			    AND (:min IS NULL OR p.preco >= :min)
			    AND (:max IS NULL OR p.preco <= :max)
			""")

	Page<Produto> buscaAvancada(@Param("nome") String nome, @Param("min") Double min, @Param("max") Double max,
			Pageable pageable);
}
