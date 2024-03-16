package nitconf.reviewermodule.reviewer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(res -> res.disable())
        
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/api/users/signup").permitAll() // Allow access to these URLs without authentication
                    .anyRequest().authenticated() // Require authentication for any other URL
            )

           

            .formLogin(formLogin -> formLogin
                .loginPage("/api/users/login") // Customize login page URL
                .permitAll() // Allow access to the login page without authentication
            )
            
            .logout(logout -> logout
                .permitAll() 
            );

        return http.build();
    }



     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
