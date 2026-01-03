package com.strart.DTOs;

import com.strart.model.TipoBory;

public record BoryDashboardDTO(
        TipoBory tipo,
        String titulo,
        String descricao,
        boolean limiteAtingido
) {}

