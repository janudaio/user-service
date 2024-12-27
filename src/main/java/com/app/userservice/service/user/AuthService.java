package com.app.userservice.service.user;

import com.app.userservice.dto.LoginRequest;
import com.app.userservice.dto.ResponseMessage;

public interface AuthService {
    ResponseMessage<String> login(LoginRequest request);
}
