package spring.code.jake.myprojects.auth.dto;

public record AuthenticationRequest(String username, String password) {
    // record类Java 16以后开始正式支持，不想装逼直接用@Value注解
}
