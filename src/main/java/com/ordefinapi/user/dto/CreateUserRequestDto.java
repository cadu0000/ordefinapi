package com.ordefinapi.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CreateUserRequestDto {
    @Schema(description = "Nome completo do usuário", example = "João da Silva", required = true)
    private String name;

    @Schema(description = "Endereço de e-mail do usuário, que será usado para login", example = "joao.silva@example.com", required = true)
    private String email;

    @Schema(description = "Senha do usuário (mínimo 8 caracteres)", example = "SenhaForte123", required = true)
    private String password;

    @Schema(description = "CPF do usuário (apenas números, 11 dígitos)", example = "12345678901", required = true)
    private String cpf;

    @Schema(description = "Data de nascimento do usuário (formato ISO 8601: yyyy-MM-dd)", example = "1990-05-20", required = true)
    private LocalDate birthday;
}
