package com.user.service;

import com.user.dto.CreateUserRequestDto;
import com.user.dto.UserResponseDto;
import com.exception.CpfAlreadyExistsException;
import com.exception.EmailAlreadyExistsException;
import com.user.model.User;
import com.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto createUser(CreateUserRequestDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> { throw new EmailAlreadyExistsException("Email já cadastrado"); });

        userRepository.findByCpf(dto.getCpf())
                .ifPresent(u -> { throw new CpfAlreadyExistsException("CPF já cadastrado"); });

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setCpf(dto.getCpf());
        user.setBirthday(dto.getBirthday());

        User savedUser = userRepository.save(user);

        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getCpf(),
                savedUser.getBirthday()
        );
    }
}
