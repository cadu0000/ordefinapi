package com.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDTO {

    @Schema(description = "Nome completo do usuário", example = "João da Silva", required = true)
    private String name;

    @Schema(description = "Endereço de e-mail do usuário, que será usado para login", example = "joao.silva@example.com", required = true)
    private String email;

    @Schema(description = "Senha do usuário (deve ter no mínimo 8 caracteres)", example = "senhaForte123", required = true)
    private String password;

}