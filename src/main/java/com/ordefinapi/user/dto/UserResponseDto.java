package com.ordefinapi.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Setter
@Getter
public class UserResponseDto {
    @Schema(description = "Identificador único do usuário", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
    private UUID userId;

    @Schema(description = "Nome completo do usuário", example = "João da Silva", required = true)
    private String name;

    @Schema(description = "Endereço de e-mail do usuário", example = "joao.silva@example.com", required = true)
    private String email;

    @Schema(description = "CPF do usuário (11 dígitos)", example = "12345678901", required = true)
    private String cpf;

    @Schema(description = "Data de nascimento do usuário", example = "1990-05-20", required = true)
    private LocalDate birthday;

    public UserResponseDto(UUID id, String name, String email, String cpf, LocalDate birthday) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthday = birthday;
    }

    public UserResponseDto() {
    }
}
