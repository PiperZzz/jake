package spring.code.jake.myproduct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.slf4j.*;

@Aspect
@Component
public class MyProductServiceAspect {
    private static final Logger logger = LoggerFactory.getLogger(MyProductServiceAspect.class);

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void myProductService() {}

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
