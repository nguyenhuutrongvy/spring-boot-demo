package com.vynht.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    // Need a controller method to show initial HTML form
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // Need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    // Need a controller to read form data and add data to the model
    @RequestMapping("/processFormVersionTwo")
    public String letShoutDude(HttpServletRequest request, Model model) {
        // Read the request parameter from the HTML form
        String theName = request.getParameter("studentName");

        // Convert the data to all caps
        StringBuilder result = new StringBuilder(theName.toUpperCase());

        // Create the message
        result.insert(0, "Yo! ");

        // Add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }

    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model model) {
        // Convert the data to all caps
        StringBuilder result = new StringBuilder(theName.toUpperCase());

        // Create the message
        result.insert(0, "Hey my friend from v3! ");

        // Add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }
}
