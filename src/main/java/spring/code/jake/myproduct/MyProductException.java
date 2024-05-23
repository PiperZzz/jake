package spring.code.jake.myproduct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
public class MyProductException extends RuntimeException {

    public MyProductException(String message) {
        super(message);
    }
}
