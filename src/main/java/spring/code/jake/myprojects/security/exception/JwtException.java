package spring.code.jake.myprojects.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.security.auth.message.AuthException;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Authorization Required")
public class JwtException extends AuthException {

    public JwtException(String message) {
        super(message);
    }
}
