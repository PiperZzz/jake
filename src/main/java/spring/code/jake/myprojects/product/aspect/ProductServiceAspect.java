package spring.code.jake.myprojects.product.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class ProductServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceAspect.class);

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void myProductService() {
    }

    @Before("myProductService()")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        logger.info("Executing method: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "myProductService()", returning = "result")
    public void logAfterServiceMethods(JoinPoint joinPoint, Object result) {
        logger.info("Method executed successfully: " + joinPoint.getSignature());
        logger.info("Returned value: " + result);
    }

    @AfterThrowing(pointcut = "myProductService()", throwing = "ex")
    public void logAfterThrowingServiceMethods(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method: " + joinPoint.getSignature() + " with message: " + ex.getMessage());
    }

    @Around("myProductService()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}
