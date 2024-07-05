package spring.code.jake.myproduct;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class MyJwtService {
    
    private final String SECRET_KEY;    
    private final long DURATION;

    public MyJwtService(@Value("${security.jwt.token.secret-key}") String secretKey, @Value("${security.jwt.token.expire-length}") long duration) {
        SECRET_KEY = secretKey;
        DURATION = duration;

    }


    public String generateToken(Authentication authentication) {// Authentication类是Spring Security中用于处理身份验证和授权的关键类
        String username = authentication.getName();// 提取用户名
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities(); // 提取用户角色集合

        Date now = new Date();
        Date validity = new Date(now.getTime() + DURATION); // 设置token的过期时间
        Claims claims = Jwts.claims().setSubject(username); // Jwts的静态方法claims()设置token的主体部分，即用户名，返回的是一个Claims对象
        claims.put("roles", roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())); // 设置token的负载部分的roles属性

        return Jwts.builder() // Jwts的静态方法builder()创建一个JwtBuilder对象
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 设置token的签名算法和密钥
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

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(token);
            return true;
        } catch (IllegalArgumentException e) {
            throw new JwtException(e.getMessage());
        }
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(token).getBody();
        String[] roles = claims.get("roles", String.class).split(",");
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "",
                Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInkey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInkey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}