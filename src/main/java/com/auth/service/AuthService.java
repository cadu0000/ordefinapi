package com.auth.service;

import com.user.dto.CreateUserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.service.UserService;
import com.auth.util.JwtUtil;
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
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public String signUp(String email, String password, String name, String cpf, LocalDate birthday) {
        CreateUserRequestDto dto = new CreateUserRequestDto();
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setName(name);
        dto.setCpf(cpf);
        dto.setBirthday(birthday);

        UserResponseDto response = userService.createUser(dto);
        return jwtUtil.generateToken(response.getEmail());
    }

    public String login(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (BadCredentialsException ex) {
            throw ex;
        }
        return jwtUtil.generateToken(email);
    }
}
