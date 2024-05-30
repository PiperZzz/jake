package spring.code.jake.myproduct;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class MyProductWebConfig implements WebMvcConfigurer {
    private final MyProductAuthorizationInterceptor myProductInterceptor;

    public MyProductWebConfig(MyProductAuthorizationInterceptor myProductInterceptor) {
        this.myProductInterceptor = myProductInterceptor;
    }

    @Override
    @SuppressWarnings("null")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myProductInterceptor).addPathPatterns("/v1/api/**");
    }
}
