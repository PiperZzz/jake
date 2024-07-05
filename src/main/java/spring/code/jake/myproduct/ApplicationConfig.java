package spring.code.jake.myproduct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    // private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(String username) {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                // return userRepository.findByUsername(username);
                return null;
            }
        };
    }
}
