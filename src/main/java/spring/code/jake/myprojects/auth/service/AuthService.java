package spring.code.jake.myprojects.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.code.jake.myprojects.auth.dto.AuthenticationRequest;
import spring.code.jake.myprojects.auth.dto.AuthenticationResponse;
import spring.code.jake.myprojects.auth.dto.RegisterRequest;
import spring.code.jake.myprojects.auth.model.User;
import spring.code.jake.myprojects.auth.repository.UserRepository;
import spring.code.jake.myprojects.auth.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .email(request.getEmail())
            .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .authenticationToken(jwtToken)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = userRepository.findByUsername(request.username()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .authenticationToken(jwtToken)
            .build();
    }
}
