package spring.code.jake.myproduct;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class MyProductWebConfig implements WebMvcConfigurer {
    private final MyProductInterceptor myProductInterceptor;

    public MyProductWebConfig(MyProductInterceptor myProductInterceptor) {
        this.myProductInterceptor = myProductInterceptor;
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(myProductInterceptor).addPathPatterns("/v1/api/**");
    }
}
