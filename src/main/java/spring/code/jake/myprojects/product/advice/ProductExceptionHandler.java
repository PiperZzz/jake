package spring.code.jake.myprojects.product.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import spring.code.jake.myprojects.product.exception.ProductException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class ProductExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ProductExceptionHandler.class);

    @ExceptionHandler(ProductException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
    public void handleMyProductException(ProductException e) {
        logger.error("An error occurred: {}", e.getMessage(), e);
    }

    @SuppressWarnings("unused") //TODO: 仅作HTTP Status Code示例
    private String commonHttpStatusCode(int i) {
        switch (i) {
            case 400:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Request");
            case 401:
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication Required");
            case 403:
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
            case 404:
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found");
            case 500:
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
            case 502:
                throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Bad Gateway");
            case 503:
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Service Unavailable");
            case 504:
                throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Gateway Timeout");
            default:
                return "200 Request Successful";
        }
    }
}