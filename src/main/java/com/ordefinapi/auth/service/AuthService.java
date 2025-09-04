package com.ordefinapi.auth.service;

import com.ordefinapi.user.dto.CreateUserRequestDto;
import com.ordefinapi.user.dto.UserResponseDto;
import com.ordefinapi.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String signUp(String email, String password, String name, String cpf, LocalDate birthday) {
        CreateUserRequestDto dto = new CreateUserRequestDto();
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setName(name);
        dto.setCpf(cpf);
        dto.setBirthday(birthday);

        UserResponseDto response = userService.createUser(dto);

        var userDetails = userService.loadUserByUsername(response.getEmail());
        return jwtService.generateToken(userDetails);
    }

    public String login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        var userDetails = userService.loadUserByUsername(email);
        return jwtService.generateToken(userDetails);
    }
}
