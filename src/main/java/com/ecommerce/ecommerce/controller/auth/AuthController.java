package com.ecommerce.ecommerce.controller.auth;


import com.ecommerce.ecommerce.model.Users;
import com.ecommerce.ecommerce.repository.auth.AuthRepository;
import com.ecommerce.ecommerce.request.auth.AddUserRequest;
import com.ecommerce.ecommerce.response.ApiResponse;
import com.ecommerce.ecommerce.service.auth.AuthService;
import com.ecommerce.ecommerce.service.auth.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("${api.prefix}/auth/")
@RequiredArgsConstructor
public class AuthController {
    final AuthService authService;

    @Autowired
    final JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody AddUserRequest addUserRequest) {
        System.out.println(addUserRequest);
        try {
            Users user = authService.register(addUserRequest);
            return ResponseEntity.ok(new ApiResponse("User registered Success", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Registration Failed", e.getMessage()));
        }
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody AddUserRequest addUserRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    addUserRequest.getEmail(), addUserRequest.getPassword()
            ));
            if(authentication.isAuthenticated()){
                String token = jwtService.generateToken(addUserRequest.getEmail());
                return ResponseEntity.ok(new ApiResponse("Login Success", "Bearer " + token));
            } else{
                return ResponseEntity.ok(new ApiResponse("Login Failed", "User Not Found"));
            }
//            Optional<Users> user = authService.login(addUserRequest);
//            return ResponseEntity.ok(new ApiResponse("User login Success", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Registration Failed", e.getMessage()));
        }

    }
}
