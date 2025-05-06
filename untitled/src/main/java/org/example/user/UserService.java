package org.example.user;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public User register(RegisterRequest req) {
        if (userMapper.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());

        // 默认字段
        user.setUsername("user_" + UUID.randomUUID().toString().substring(0, 8));
        user.setFullName("Anonymous");
        user.setBalance(BigDecimal.ZERO);

        userMapper.insertUser(user);
        return user;
    }


    public Optional<User> login(String email, String password) {
        return userMapper.login(email, password);
    }

    public void changePassword(ChangePasswordRequest req) {
        Optional<User> userOpt = userMapper.findByEmail(req.getEmail());
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Email not registered");
        }
        userMapper.updatePassword(req.getEmail(), req.getNewPassword());
    }

    public Optional<User> getUserById(UUID id) {
        return userMapper.findById(id);
    }

    public void updateUserInfo(User updatedUser) {
        Optional<User> existingUserOpt = userMapper.findById(updatedUser.getId());
        if (existingUserOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        userMapper.updateUserInfo(updatedUser);
    }


}
