package spring.code.jake.myprojects.product.interceptor;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler)
            throws Exception {
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull Object handler, @Nullable ModelAndView modelAndView) 
            throws Exception {
        // 只有preHandle返回true时才会执行
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, @Nullable Exception ex)
            throws Exception {
        // 只有preHandle返回true时才会执行
    }
}
