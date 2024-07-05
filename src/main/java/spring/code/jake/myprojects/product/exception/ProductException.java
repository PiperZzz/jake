package spring.code.jake.myprojects.product.exception;

import org.springframework.web.client.ResourceAccessException;

public class ProductException extends ResourceAccessException {

    public ProductException(String message) {
        super(message);
    }
}
