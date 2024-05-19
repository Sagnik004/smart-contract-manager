package in.sagnikchakraborty.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")
    public String getHomePage(Model model) {
        System.out.println("Home page handler");

        // Sending data to view
        model.addAttribute("name", "John Doe");
        model.addAttribute("address", "225 Baker Avenue");
        model.addAttribute("city", "Mukesh Nagar");
        return "home";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        System.out.println("About page handler");
        return "about";
    }

    @GetMapping("/services")
    public String getServicesPage() {
        System.out.println("Services page handler");
        return "services";
    }

    @GetMapping("/contact")
    public String getContactPage() {
        System.out.println("Contact page handler");
        return "contact";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        System.out.println("Login page handler");
        return "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        System.out.println("Signup page handler");
        return "signup";
    }
}
