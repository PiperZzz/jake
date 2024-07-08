package spring.code.jake.myprojects.auth.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.code.jake.myprojects.auth.dto.AuthenticationRequest;
import spring.code.jake.myprojects.auth.dto.AuthenticationResponse;
import spring.code.jake.myprojects.auth.dto.RegisterRequest;
import spring.code.jake.myprojects.auth.service.AuthService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("refresh-token")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }
}