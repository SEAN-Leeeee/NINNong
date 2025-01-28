package com.NINNong.nonggu.controller;


import com.NINNong.nonggu.dto.MemberSignupRequest;
import com.NINNong.nonggu.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final AuthenticationService authenticationService;

    public MemberController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign")
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequest request) {
        authenticationService.signup(request.getEmail(), request.getPassword(), request.getName());
        return ResponseEntity.ok("회원가입 성공!");
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String email, @RequestParam String password) {
        boolean isValid = authenticationService.login(email, password);
        return ResponseEntity.ok(isValid);
    }
}
