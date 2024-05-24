package spring.code.jake.myproduct;

import org.springframework.web.client.ResourceAccessException;

public class MyProductException extends ResourceAccessException {

    public MyProductException(String message) {
        super(message);
    }
}
