package com.strart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.strart.model.Bory;
import com.strart.model.TipoBory;

public interface BoryRepository extends JpaRepository<Bory, Long> {
    List<Bory> findByTipo(TipoBory tipo);
}

