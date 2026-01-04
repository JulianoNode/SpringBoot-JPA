package com.strart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strart.model.TemaCorCSS;

public interface TemaCorCSSRepository extends JpaRepository<TemaCorCSS, Long> {

    Optional<TemaCorCSS> findByAtivoTrue();
}


