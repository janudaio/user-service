package com.app.userservice.service.impl.user;

import com.app.userservice.entity.user.UserEntity;
import com.app.userservice.repository.user.UserRepo;
import com.app.userservice.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo daouser;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String registerUser(String username, String password, String email) {
        if (daouser.findByUsername(username) != null) {
            return "Username is already taken!";
        }
        if (daouser.findByEmail(email) != null) {
            return "Email is already in use!";
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);

        daouser.save(user);
        return user.getUsername();
    }

//    @Override
//    public String getUserDashboard(String username) {
//        UserEntity user = daouser.findByUsername(username);
//        if (user == null) {
//            return "User not found!";
//        }
//        return "Welcome " + user.getUsername() + "!";
//    }
}
