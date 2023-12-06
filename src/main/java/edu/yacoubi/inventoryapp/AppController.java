package edu.yacoubi.inventoryapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("")
    String viewHomePage() {
        return "index";
    }
}
