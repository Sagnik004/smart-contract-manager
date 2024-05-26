package in.sagnikchakraborty.config;

import in.sagnikchakraborty.service.impl.SecurityCustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailService customUserDetailService;

    /* Configuration of authentication provider */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // Object of user detail service
        provider.setUserDetailsService(customUserDetailService);

        // Object of password encoder
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    /* Configuration of Http Security */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /* Configure:
            1) Open and authenticated routes
            2) Form login and login route
         */
        http
            .authorizeHttpRequests((authorize) -> authorize.
                requestMatchers("/user/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticate")
                    .defaultSuccessUrl("/user/profile")
                    .usernameParameter("email")
                    .passwordParameter("password")
            );

        // Temporary (when csrf is enabled, which is enabled by default, then logout URL must be a POST request.
        // Getting around it for testing)
        // http.csrf(AbstractHttpConfigurer::disable);

        http.logout(logoutForm -> logoutForm
                .logoutUrl("/do-logout")
                .logoutSuccessUrl("/login?logout=true")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
