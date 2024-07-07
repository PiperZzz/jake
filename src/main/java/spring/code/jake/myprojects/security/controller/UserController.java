package spring.code.jake.myprojects.security.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.code.jake.myprojects.security.service.CustomUserDetailsService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("auth")
    public ResponseEntity<String> auth(@RequestBody String username) {
        customUserDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok().build();
    }

}