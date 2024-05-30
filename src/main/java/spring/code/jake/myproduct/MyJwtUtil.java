package spring.code.jake.myproduct;

import io.jsonwebtoken.*;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class MyJwtUtil {
    @Value("${security.jwt.token.secret-key}")
    private static String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private static long validityInMilliseconds;

    public static String generateToken(Authentication authentication) {// Authentication类是Spring Security中用于处理身份验证和授权的关键类
        String username = authentication.getName();// 提取用户名
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities(); // 提取用户角色集合

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds); // 设置token的过期时间
        Claims claims = Jwts.claims().setSubject(username); // Jwts的静态方法claims()设置token的主体部分，即用户名，返回的是一个Claims对象
        claims.put("roles", roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())); // 设置token的负载部分的roles属性

        return Jwts.builder() // Jwts的静态方法builder()创建一个JwtBuilder对象
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey) // 设置token的签名算法和密钥
                .compact(); // 生成token
    }

    public static String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }

    public static boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (IllegalArgumentException e) {
            throw new JwtException(e.getMessage());
        }
    }

    public static String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public static Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String[] roles = claims.get("roles", String.class).split(",");
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "",
                Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}