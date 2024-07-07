package spring.code.jake.myprojects.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import spring.code.jake.myprojects.auth.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter JwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return null;
        // httpSecurity
        //         .csrf(csrf -> csrf.disable())
        //         .authorizeRequests(requests -> requests.requestMatchers("").permitAll().anyRequest().authenticated())
        //         .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        //         .authenticationProvider(null)
        //         .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // return httpSecurity.build();
    }
}
