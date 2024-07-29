package spring.code.jake.myprojects.auth.dto;

import lombok.Value;

@Value // record类Java 16以后开始正式支持，不想装逼直接用@Value注解
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
