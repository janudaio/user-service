package com.app.userservice.service.impl.user;

import com.app.userservice.dto.LoginRequest;
import com.app.userservice.dto.ResponseMessage;
import com.app.userservice.entity.user.UserEntity;
import com.app.userservice.repository.user.UserRepo;
import com.app.userservice.service.user.AuthService;
import com.app.userservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseMessage<String> login(LoginRequest request) {
        UserEntity user = userRepo.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new ResponseMessage<>("Invalid username or password", null);
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return new ResponseMessage<>("Login successful", token);
    }
}
