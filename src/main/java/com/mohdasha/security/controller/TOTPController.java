package com.mohdasha.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/totp")
public class TOTPController {
    @GetMapping("/login")
    public String totpLogin() {
        return "totp-login";
    }

    @GetMapping("/error")
    public String totpError(Model model) {
        model.addAttribute("loginError", true);
        return "totp-login";
    }
}
