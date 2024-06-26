package spring.code.jake.myproduct;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@SuppressWarnings("null")
public class MyProductWebConfig implements WebMvcConfigurer {
    private final MyProductAuthorizationInterceptor myProductAuthorizationInterceptor;
    private final MyProductAuthenticationInterceptor myProductAuthenticationInterceptor;

    public MyProductWebConfig(MyProductAuthorizationInterceptor myProductAuthorizationInterceptor,
            MyProductAuthenticationInterceptor myProductAuthenticationInterceptor) {
        this.myProductAuthorizationInterceptor = myProductAuthorizationInterceptor;
        this.myProductAuthenticationInterceptor = myProductAuthenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(myProductAuthorizationInterceptor).addPathPatterns("/v1/api/**");
        // registry.addInterceptor(myProductAuthenticationInterceptor).addPathPatterns("/v1/api/**");
        // TODO: 用Spring Security的Filter来做Security，Interceptor用于其它拦截任务。
    }
}
