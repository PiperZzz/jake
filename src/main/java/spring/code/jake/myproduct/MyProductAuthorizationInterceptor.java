// package spring.code.jake.myproduct;

// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// @SuppressWarnings("null")
// public class MyProductAuthorizationInterceptor implements HandlerInterceptor {

//     private final MyJwtService myJwtService;

//     public MyProductAuthorizationInterceptor(MyJwtService myJwtService) {
//         this.myJwtService = myJwtService;
//     }

//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//             throws Exception {
//         String bearerToken = MyJwtService.resolveToken(request);
//         if (bearerToken != null) {
//             return myJwtService.isTokenValid(bearerToken);
//         } else {
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             return false; // 身份验证失败
//         }
//     }

//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//             ModelAndView modelAndView) throws Exception {
//         // 只有preHandle返回true时才会执行
//     }

//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//             throws Exception {
//         // 只有preHandle返回true时才会执行
//     }
// }
