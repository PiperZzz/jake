package spring.code.jake.myproduct;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.*;

@RestControllerAdvice
public class MyProductExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyProductExceptionHandler.class);

    @ExceptionHandler(MyProductException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
    public void handleMyProductException(MyProductException e) {
        logger.error("An error occurred: {}", e.getMessage(), e);
    }

    
    @SuppressWarnings("unused") // 仅作HTTP Status Code示例
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