package com.NINNong.nonggu.controller;


import com.NINNong.nonggu.dto.UserSignupRequest;
import com.NINNong.nonggu.service.AuthenticationService;
import com.NINNong.nonggu.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    public UserController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/sign")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) {
        userService.signUp(request);
        return ResponseEntity.ok("회원가입 성공!");
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String email, @RequestParam String password) {
        boolean isValid = userService.login(email, password);
        return ResponseEntity.ok(isValid);
    }
}
