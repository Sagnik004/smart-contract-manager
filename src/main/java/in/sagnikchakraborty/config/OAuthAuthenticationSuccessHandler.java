package in.sagnikchakraborty.config;

import in.sagnikchakraborty.entities.Providers;
import in.sagnikchakraborty.entities.User;
import in.sagnikchakraborty.helpers.AppConstants;
import in.sagnikchakraborty.repository.IUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private IUserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res,
                                        Authentication auth)
            throws IOException, ServletException {
        log.info("OAuth authentication success!");

        // Save user info to DB
        DefaultOAuth2User user = (DefaultOAuth2User) auth.getPrincipal();

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");
        String picture = user.getAttribute("picture");

        User newUser = new User.Builder()
                .name(name)
                .email(email)
                .isEmailVerified(true)
                .profilePic(picture)
                .userId(UUID.randomUUID().toString())
                .provider(Providers.GOOGLE)
                .providerId(user.getName())
                .isEnabled(true)
                .roleList(List.of(AppConstants.ROLE_USER))
                .build();

        Optional<User> dbUser = userRepository.findByEmail(email);
        if (dbUser.isEmpty()) {
            userRepository.save(newUser);
            log.info("User {} saved successfully!", newUser);
        }

        res.sendRedirect("/user/profile");
    }
}
