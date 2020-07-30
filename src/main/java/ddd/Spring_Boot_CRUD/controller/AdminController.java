package ddd.Spring_Boot_CRUD.controller;

import ddd.Spring_Boot_CRUD.model.User;
import ddd.Spring_Boot_CRUD.repository.RoleRepository;
import ddd.Spring_Boot_CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/panel")
    public String userList(@RequestParam(name = "new", required = false, defaultValue = "false") boolean newUser, Model model) {
        model.addAttribute("users", userService.allUsers());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("newUser", newUser);
        return "admin-panel";
    }
}
