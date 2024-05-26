package in.sagnikchakraborty.controller;

import in.sagnikchakraborty.dto.UserSignupForm;
import in.sagnikchakraborty.entities.User;
import in.sagnikchakraborty.helpers.Message;
import in.sagnikchakraborty.helpers.MessageColor;
import in.sagnikchakraborty.helpers.MessageType;
import in.sagnikchakraborty.service.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String indexPage() {
        return "redirect:/home";
    }

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
        model.addAttribute("userSignupForm", userForm);

        return "signup";
    }

    // Process signup request
    @PostMapping("/register")
    public String doRegister(@Valid @ModelAttribute UserSignupForm userForm,
                             BindingResult bindingResult, HttpSession session) {
        // Validate data
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        // Save to DB
        User user = new User.Builder()
                .name(userForm.getName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .phoneNumber(userForm.getPhoneNumber())
                .about(userForm.getAbout())
                .isEnabled(true)
                .build();

        User savedUser = userService.saveUser(user);
        System.out.println("User saved successfully! " + savedUser);

        // Set message in session & redirect
        Message message = new Message.Builder()
                .type(MessageType.SUCCESS)
                .color(MessageColor.green)
                .content("Sign up complete!")
                .build();

        session.setAttribute("message", message);
        return "redirect:/signup";
    }
}
