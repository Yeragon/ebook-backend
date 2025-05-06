package org.example.user;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Optional;
import java.util.UUID;

@Mapper // 或更准确地指定 mapper 包
public interface UserMapper {
    void insertUser(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> login(String email, String password);

    void updatePassword(String email, String newPassword);

    Optional<User> findById(UUID id);

    // User information modification and saving
    void updateUserInfo(User user);




}
