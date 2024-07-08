package spring.code.jake.myprojects.auth.dto;

import lombok.Value;

@Value
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
