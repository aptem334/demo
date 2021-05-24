package com.neoflex.demo;

import com.neoflex.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(path="/user")
public class UserController  {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Users user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(user);
        return "redirect:/user/index";
    }

    @GetMapping(path="/add")
    public String addNewUser(@Valid Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "result";
        }
        userRepository.save(user);
        return "redirect:/user/all";
    }

    @GetMapping(path="/edit/{id}/name")
    public String editUserName (@PathVariable("id") Integer id, @RequestParam String name) {
        Users user = userRepository.findById(id).get();
        user.setName(name);
        userRepository.save(user);
        return "redirect:/user/all";
    }

    @GetMapping(path="/edit/{id}/email")
    public String editUserEmail (@PathVariable("id") Integer id, @RequestParam String email) {
        Users user = userRepository.findById(id).get();
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/user/all";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Users user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid Users user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        return "redirect:/user/all";
    }
    @GetMapping(path="/delete/{id}")
    public String deleteUser (@PathVariable("id") Integer id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/user/all";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }
}