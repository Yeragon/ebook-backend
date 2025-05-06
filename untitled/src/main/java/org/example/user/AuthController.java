package org.example.user;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody RegisterRequest req) {
        return ApiResponse.success(userService.register(req));
    }


    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");

        return userService.login(email, password)
                .map(user -> Map.of(
                        "message", "Login successful",
                        "userId", user.getId()
                ))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    @PostMapping("/changepassword")
    public ApiResponse<String> changePassword(@RequestBody ChangePasswordRequest req) {
        userService.changePassword(req);
        return ApiResponse.success("Password changed successfully");
    }

    @GetMapping("/user/info/{id}")
    public ApiResponse<User> getUserInfo(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ApiResponse::success)
                .orElseGet(() -> ApiResponse.fail("User not found"));
    }

    @PostMapping("/account/update")
    public ApiResponse<String> updateUserInfo(@RequestBody User updatedUser) {
        userService.updateUserInfo(updatedUser);
        return ApiResponse.success("User information updated successfully");
    }


}
