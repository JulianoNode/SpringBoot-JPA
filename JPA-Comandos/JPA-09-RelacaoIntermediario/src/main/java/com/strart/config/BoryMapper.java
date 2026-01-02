package com.strart.config;

import com.strart.DTOs.BoryDTO;
import com.strart.model.Bory;

public class BoryMapper {

    public static BoryDTO toDTO(Bory bory) {
        BoryDTO dto = new BoryDTO();
        dto.setId(bory.getId());
        dto.setTitulo(bory.getTitulo());
        dto.setDescricao(bory.getDescricao());
        dto.setImagem(bory.getImagem());
        dto.setTipo(bory.getTipo());
        return dto;
    }
}
