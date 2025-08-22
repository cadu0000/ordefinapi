package com.transactions.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class TransactionRequestDTO {

    @Schema(description = "Valor da transação", example = "100.50", required = true)
    private BigDecimal amount;

    @Schema(description = "Data da transação", example = "2025-08-11", required = true)
    private LocalDate date;

    @Schema(description = "Tipo da transação (ex: 'INCOME' ou 'EXPENSE')", example = "EXPENSE", required = true)
    private String type;

    @Schema(description = "ID da categoria da transação", example = "1")
    private Long categoryId;

    @Schema(description = "Descrição da transação", example = "Compra de material de escritório")
    private String description;

}
