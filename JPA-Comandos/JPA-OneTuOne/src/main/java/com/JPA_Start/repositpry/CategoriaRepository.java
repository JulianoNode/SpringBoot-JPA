package com.JPA_Start.repositpry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JPA_Start.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
