package spring.code.jake.myproduct;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.*;

@Component
@SuppressWarnings("null")
public class MyProductAuthenticationInterceptor implements HandlerInterceptor {

    private final MyJwtService myJwtService;

    public MyProductAuthenticationInterceptor(MyJwtService myJwtService) {
        this.myJwtService = myJwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String bearerToken = MyJwtService.resolveToken(request);
        if (bearerToken != null) {
            return myJwtService.isTokenValid(bearerToken); // Update Authentication Logic 
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Update Authentication Exception
            return false; // 身份验证失败
        }
    }

}