package ddd.Spring_Boot_CRUD.controller;

import ddd.Spring_Boot_CRUD.model.Role;
import ddd.Spring_Boot_CRUD.model.User;
import ddd.Spring_Boot_CRUD.repository.RoleRepository;
import ddd.Spring_Boot_CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/userList")
    public List< User > getUserList() {
        return userService.allUsers();
    }

    @GetMapping("/allRoles")
    public List< Role > getRoles() {
        return roleRepository.findAll();
    }

    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
