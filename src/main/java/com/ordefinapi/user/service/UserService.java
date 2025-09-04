package com.ordefinapi.user.service;

import com.ordefinapi.user.dto.CreateUserRequestDto;
import com.ordefinapi.user.dto.UserResponseDto;
import com.ordefinapi.exception.CpfAlreadyExistsException;
import com.ordefinapi.exception.EmailAlreadyExistsException;
import com.ordefinapi.user.model.User;
import com.ordefinapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
    }
}
