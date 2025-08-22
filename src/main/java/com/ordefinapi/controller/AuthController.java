package com.ordefinapi.controller;

import com.user.dto.CreateUserRequestDto;
import com.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Operações relacionadas à autenticação e cadastro de usuários")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody CreateUserRequestDto dto) {
        String createdUserToken = authService.signUp(
                dto.getEmail(),
                dto.getPassword(),
                dto.getName(),
                dto.getCpf(),
                dto.getBirthday()
        );
        return ResponseEntity.status(201).body(createdUserToken);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        String token = authService.login(email, password);
        return ResponseEntity.ok(token);
    }


}