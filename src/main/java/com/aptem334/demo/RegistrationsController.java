package com.aptem334.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationsController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("registrations", new Registrations());
        return "greeting";
    }

    @PostMapping("/registration")
    public String registrationSubmit(@ModelAttribute Registrations registrations, Model model) {
 //       model.addAttribute("id", registrations.getId());
        model.addAttribute("registrations", registrations);
        registrations.setId(counter.incrementAndGet());
        return "result";
    }

}