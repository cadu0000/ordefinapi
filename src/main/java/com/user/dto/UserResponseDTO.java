package com.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserResponseDTO {

    @Schema(description = "ID único do usuário gerado pelo sistema", example = "ccc20c5f-7394-4221-a378-c9d2b3ce2983")
    private UUID id;

    @Schema(description = "Nome completo do usuário", example = "João da Silva")
    private String name;

    @Schema(description = "Endereço de e-mail do usuário", example = "joao.silva@example.com")
    private String email;

}