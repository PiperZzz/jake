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

    //TODO: @RestControllerAdvice除了用于处理Controller层的异常（Handle Controller Exceptions），还可以用于以下用途：

// 1. **全局数据绑定（Global Data Binding）**: 可以在所有的Controller中共享数据。例如，可以在所有的Controller中添加一个公共的Model属性。

// 2. **全局数据预处理（Global Data Preprocessing）**: 可以在请求到达Controller之前，进行一些全局的数据预处理操作。例如，可以在请求之前统一对某些数据进行格式化。

// 3. **全局数据校验（Global Data Validation）**: 可以统一进行一些全局的数据校验操作。例如，统一对某些请求参数进行合法性检查。

// 4. **全局拦截处理（Global Interception Handling）**: 可以统一进行一些全局的拦截操作。例如，对所有的请求进行日志记录或权限检查。

// **Global Data Binding**: 在所有Controller中共享数据，例如通过添加@ModelAttribute方法。

// **Global Data Preprocessing**: 在请求到达Controller之前预处理数据，例如通过添加@InitBinder方法。

// **Global Data Validation**: 统一校验请求参数，例如通过添加自定义校验逻辑。

// **Global Interception Handling**: 统一进行日志记录、权限检查等操作。

// 通过这些功能，@RestControllerAdvice可以帮助开发者更好地管理和组织全局性的逻辑，提高代码的可维护性和可读性。
}