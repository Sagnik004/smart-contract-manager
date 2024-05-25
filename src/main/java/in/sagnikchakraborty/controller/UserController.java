package in.sagnikchakraborty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // User dashboard page
    @GetMapping("/dashboard")
    public String getUserDashboard() {
        return "user/dashboard";
    }

    // User profile page
    @GetMapping("/profile")
    public String getUserProfile() {
        return "user/profile";
    }

    // User add contact page


    // User view contacts page


    // User edit contact page


    // User delete contact page


}
