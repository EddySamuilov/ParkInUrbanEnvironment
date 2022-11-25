package com.example.carpark.web;

import com.example.carpark.dto.RegisterDTO;
import com.example.carpark.service.impl.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("registerDTO")) {
            model.addAttribute("registerDTO", new RegisterDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(
            @Valid @ModelAttribute("registerDTO") RegisterDTO registerDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        boolean isEqualPasswords = registerDTO.getPassword().equals(registerDTO.getConfirmPassword());
        if (bindingResult.hasErrors() || !isEqualPasswords) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.
                    addFlashAttribute("org.springframework.validation.BindingResult.registerDTO",
                            bindingResult);
            return "redirect:/users/register";
        }

        if (userService.isUserExists(registerDTO.getUsername())) {
            bindingResult.rejectValue
                    ("username", "error.username", "An account with this username already exists.");
            return "register";
        }

        userService.registerUser(registerDTO);

        return "redirect:/";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes attributes
    ) {

        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", userName);

        return "login";
    }



    @GetMapping("/profile")
    String getFullStatistics(Model model, @AuthenticationPrincipal Principal principal) throws NotFoundException {
        model.addAttribute("username", principal.getName());
        model.addAttribute("user", this.userService.getByName(principal.getName()));
        return "profile";
    }

}