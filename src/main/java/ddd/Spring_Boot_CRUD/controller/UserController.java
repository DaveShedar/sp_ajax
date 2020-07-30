package ddd.Spring_Boot_CRUD.controller;

import ddd.Spring_Boot_CRUD.model.Role;
import ddd.Spring_Boot_CRUD.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping(value = "/user")
    public String getHomePage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Optional< Role > adminRole = user.getRoles().stream()
                .filter(role -> role.getAuthority().equals("ROLE_ADMIN"))
                .findAny();
        model.addAttribute("isAdmin", adminRole.isPresent());
        return "user";
    }
}
