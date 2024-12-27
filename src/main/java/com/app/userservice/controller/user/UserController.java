package com.app.userservice.controller.user;

import com.app.userservice.dto.ResponseMessage;
import com.app.userservice.dto.user.UserRequest;
import com.app.userservice.service.user.UserService;
import com.app.userservice.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ResponseMessage<String>> registerUser(@Valid @RequestBody UserRequest request) {
        try {
            String user = userService.registerUser(
                    request.getUsername(),
                    request.getPassword(),
                    request.getEmail()
            );
            return ResponseEntity.ok(new ResponseMessage<>("User registered successfully", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage<>(e.getMessage(), null));
        }
    }
    }
