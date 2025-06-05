package com.ecommerce.ecommerce.service.auth;

import com.ecommerce.ecommerce.exception.UserNotFoundException;
import com.ecommerce.ecommerce.model.Users;
import com.ecommerce.ecommerce.repository.auth.AuthRepository;
import com.ecommerce.ecommerce.request.auth.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    final AuthRepository authRepository;

    final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    @Override
    public Users register(AddUserRequest addUserRequest) {
        Users user = Users.builder()
                .email(addUserRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(addUserRequest.getPassword()))
                .name(addUserRequest.getName())
                .build();
        return authRepository.save(user);
    }

    @Override
    public Optional<Users> login(AddUserRequest addUserRequest) {
        return Optional.ofNullable(authRepository
                .findByEmailAndPassword(addUserRequest.getEmail(), addUserRequest.getPassword())
                .orElseThrow(() -> new UserNotFoundException("User Not Found")));
    }
}
