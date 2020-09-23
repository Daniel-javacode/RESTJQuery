package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.Role;
import web.model.User;

import web.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import java.util.*;

@Controller
@RequestMapping("/")
public class UserController extends HttpServlet {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //login
    @GetMapping(value = "login")
    public String loginGet() {

        return "login";
    }

    //admin
    @GetMapping(value = "admin")
    public String adminGet(ModelMap model, HttpSession httpSession) {
        model.addAttribute("user", httpSession.getAttribute("user"));

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);

        List<Role> allRoles = userService.getAllRoles();
        model.addAttribute("roles", allRoles);

        Map<User, List<List<String>>> usersWithRoles = new HashMap<>();
        allUsers.forEach(user -> usersWithRoles.put(user, userService.getUserRoles(allRoles, user)));

        model.addAttribute("usersWithRoles", usersWithRoles);

        return "admin";
    }

    //admin-add
    @PostMapping(value = "admin/add")
    public String adminAddPost(User user, String[] roleIds) {
        user.setRole(userService.getRoles(roleIds));
        userService.insert(user);

        return "redirect:/admin";
    }

    //admin-edit
    @GetMapping(value = "admin/edit")
    public String adminEditGet(ModelMap model, @RequestParam("id") Long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);

        List<Role> roles = userService.getAllRoles();
        roles.forEach(role -> role.setInUser(user.isRoleInUser(role)));

        model.addAttribute("roles", roles);

        return "admin/edit";
    }

    //admin-delete
    @PostMapping(value = "admin/delete")
    public String adminDeletePost(@RequestParam("id") Long id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }

    //user
    @GetMapping(value = "user")
    public String userGet(ModelMap modelMap, HttpSession httpSession) {
        modelMap.addAttribute("user", httpSession.getAttribute("user"));

        return "user";
    }

}
