package in.sagnikchakraborty.helpers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {

    private static final Logger log = LoggerFactory.getLogger(SessionHelper.class);

    public static void removeMessageFromSession() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                                            .getRequest();
            HttpSession session = request.getSession();
            session.removeAttribute("message");
        } catch (RuntimeException ex) {
            log.info("in.sagnikchakraborty.helpers.SessionHelper.removeMessage(): Runtime exception");
            ex.printStackTrace();
        }
    }
}
