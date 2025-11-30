package com.Strart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Strart.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}