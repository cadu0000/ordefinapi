package com.ordefinapi.controller;

import com.transactions.dto.TransactionRequestDTO;
import com.transactions.dto.TransactionResponseDTO;
import com.transactions.service.TransactionService;
import com.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Operações relacionadas a transações")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Operation(
            summary = "Cria uma nova transação",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transação criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                    @ApiResponse(responseCode = "401", description = "Não autorizado")
            }
    )
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO dto,
                                                                    @AuthenticationPrincipal User user) {
        TransactionResponseDTO created = transactionService.createTransaction(dto, user.getId());
        return ResponseEntity.ok(created);
    }

    @GetMapping("/transactions")
    @Operation(
            summary = "Lista todas as transações do usuário autenticado",
            description = "Retorna uma lista de transações associadas ao usuário logado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de transações retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TransactionResponseDTO.class))
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Não autorizado")
            }
    )
    public ResponseEntity<List<TransactionResponseDTO>> listTransactions(@AuthenticationPrincipal User user) {
        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByUser(user.getId());
        return ResponseEntity.ok(transactions);
    }
}
