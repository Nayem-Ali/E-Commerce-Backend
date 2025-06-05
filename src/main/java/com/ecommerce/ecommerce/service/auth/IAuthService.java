package com.ecommerce.ecommerce.service.auth;

import com.ecommerce.ecommerce.model.Users;
import com.ecommerce.ecommerce.request.auth.AddUserRequest;

import java.util.Optional;

public interface IAuthService {
    Users register(AddUserRequest addUserRequest);
    Optional<Users> login(AddUserRequest addUserRequest);
}
