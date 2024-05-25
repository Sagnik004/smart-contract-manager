package in.sagnikchakraborty.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService getUserDetails() {
        UserDetails user1 = User.withUsername("admin")
                .password("{noop}rootuser123")
                .roles("USER", "ADMIN")
                .build();

        UserDetails user2 = User.withUsername("rocky")
                .password("{noop}rockybhai")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
