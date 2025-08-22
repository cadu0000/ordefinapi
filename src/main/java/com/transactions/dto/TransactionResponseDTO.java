package com.transactions.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Resposta da transação")
public class TransactionResponseDTO {
    @Schema(description = "ID da transação", example = "abc123")
    private UUID id;

    @Schema(description = "Valor da transação", example = "100.50")
    private BigDecimal amount;

    @Schema(description = "Data da transação", example = "2025-08-11")
    private LocalDate date;

    @Schema(description = "Tipo da transação", example = "EXPENSE")
    private String type;

    @Schema(description = "Nome da categoria", example = "Alimentação")
    private String categoryName;

    @Schema(description = "Descrição da transação", example = "Compra de supermercado")
    private String description;
}
