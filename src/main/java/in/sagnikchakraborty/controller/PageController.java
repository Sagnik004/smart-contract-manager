package in.sagnikchakraborty.controller;

import in.sagnikchakraborty.dto.UserSignupForm;
import in.sagnikchakraborty.entities.User;
import in.sagnikchakraborty.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @Autowired
    private IUserService userService;

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
    public String getSignupPage(Model model) {

        // Send a blank UserSignupForm object to UI
        UserSignupForm userForm = new UserSignupForm();
        model.addAttribute("userForm", userForm);

        return "signup";
    }

    // Process signup request
    @PostMapping("/register")
    public String doRegister(@ModelAttribute UserSignupForm userForm) {
        // Validate data

        // Save to DB
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());

        userService.saveUser(user);

        // Set message & redirect
        return "redirect:/signup";
    }
}
