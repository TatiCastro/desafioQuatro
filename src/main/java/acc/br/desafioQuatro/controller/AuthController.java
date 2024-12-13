package acc.br.desafioQuatro.controller;

import acc.br.desafioQuatro.model.Login;
import acc.br.desafioQuatro.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String loginPage() {
        return "login";
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Login user = loginService.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return "redirect:/studentRegistration";
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
    @GetMapping("/login/register")
    public String showRegistrationPage() {
        return "register";
    }

    @PostMapping("/login/register")
    public String register(@ModelAttribute Login login) {
        loginService.register(login);
        return "redirect:/login";
    }
}


