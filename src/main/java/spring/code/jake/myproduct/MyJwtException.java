package spring.code.jake.myproduct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.security.auth.message.AuthException;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "JWT Token Authentication Required")
public class MyJwtException extends AuthException {
    
    public MyJwtException(String message) {
        super(message);
    }
}
