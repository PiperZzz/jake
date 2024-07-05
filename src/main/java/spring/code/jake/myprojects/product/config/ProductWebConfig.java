package spring.code.jake.myprojects.product.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;
import spring.code.jake.myprojects.product.interceptor.ProductInterceptor;
import spring.code.jake.myprojects.product.interceptor.UserInterceptor;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@AllArgsConstructor
public class ProductWebConfig implements WebMvcConfigurer {
    private final UserInterceptor userInterceptor;
    private final ProductInterceptor productInterceptor;

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor).addPathPatterns("user/api/v1/**");
        registry.addInterceptor(productInterceptor).addPathPatterns("product/api/v1/**");
        // TODO: 用Spring Security的Filter来做Security，Interceptor用于其它拦截任务。
    }
}
