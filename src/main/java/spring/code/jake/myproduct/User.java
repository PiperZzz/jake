// package spring.code.jake.myproduct;

// import java.util.*;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import lombok.*;

// import jakarta.persistence.*;

// @Data
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Table(name = "my_user")
// public class User implements UserDetails {
    
//     @Id
//     @GeneratedValue
//     private Integer id;
    
    
//     private String username;
//     private String password;
//     private boolean enabled;

//     private Collection<? extends GrantedAuthority> authorities;
//     private boolean credentialsNonExpired;
//     private boolean accountNonExpired;
//     private boolean accountNonLocked;

//     @Override
//     public String getUsername() {
//         return username;
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public boolean isEnabled() {
//         return enabled;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return authorities;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return credentialsNonExpired;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return accountNonExpired;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return accountNonLocked;
//     }
// }
