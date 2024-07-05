package spring.code.jake.myproduct;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken (UserDetails userDetails) {
        Map<String, Object> Claims = new HashMap<>();
        return createToken(Claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> extractClaims, String subject) {

        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + DURATION))
                .signWith(getSignInkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
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