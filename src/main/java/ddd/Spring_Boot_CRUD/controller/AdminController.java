package ddd.Spring_Boot_CRUD.controller;


import ddd.Spring_Boot_CRUD.model.User;
import ddd.Spring_Boot_CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "list-users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/list";
    }

    @GetMapping(value = "/addUser")
    public String showFormForAdd(User user) {
        return "add-user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/list";
    }

    @GetMapping(value = "/updateForm/{id}")
    public String showFormForUpdate(@PathVariable(name = "id") Long id,
                                    Model theModel) {
        User user = userService.findUserById(id);
        theModel.addAttribute("user", user);
        return "user-form";
    }
}
