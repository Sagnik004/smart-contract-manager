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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

        // Get user from authentication object
        var oAuth2User = (DefaultOAuth2User) auth.getPrincipal();

        // Identify the provider
        var oAuth2AuthToken = (OAuth2AuthenticationToken) auth;
        String registrationId = oAuth2AuthToken.getAuthorizedClientRegistrationId();

        // Build User entity using auth principal
        User newUser = buildUserFromOAuthPrincipal(oAuth2User, registrationId);

        // Save user info to DB
        Optional<User> dbUser = userRepository.findByEmail(newUser.getEmail());
        if (dbUser.isEmpty()) {
            userRepository.save(newUser);
            log.info("User {} saved successfully!", newUser);
        }

        res.sendRedirect("/user/profile");
    }

    private static User buildUserFromOAuthPrincipal(DefaultOAuth2User oAuth2User,
                                                    String providerName) {
        if (providerName.equalsIgnoreCase("google")) {
            return new User.Builder()
                    .name(oAuth2User.getAttribute("name"))
                    .email(oAuth2User.getAttribute("email"))
                    .isEmailVerified(true)
                    .profilePic(oAuth2User.getAttribute("picture"))
                    .userId(UUID.randomUUID().toString())
                    .provider(Providers.GOOGLE)
                    .providerId(oAuth2User.getName())
                    .isEnabled(true)
                    .roleList(List.of(AppConstants.ROLE_USER))
                    .build();
        }

        if (providerName.equalsIgnoreCase("github")) {
            String email = oAuth2User.getAttribute("email");
            if (email == null) {
                email = oAuth2User.getAttribute("login") + "@github.com";
            }
            return new User.Builder()
                    .name(oAuth2User.getAttribute("name"))
                    .email(email)
                    .isEmailVerified(true)
                    .profilePic(oAuth2User.getAttribute("avatar_url"))
                    .userId(UUID.randomUUID().toString())
                    .provider(Providers.GITHUB)
                    .providerId(Integer.toString(oAuth2User.getAttribute("id")))
                    .isEnabled(true)
                    .roleList(List.of(AppConstants.ROLE_USER))
                    .build();
        }

        throw new RuntimeException("Invalid OAuth provider");
    }
}
