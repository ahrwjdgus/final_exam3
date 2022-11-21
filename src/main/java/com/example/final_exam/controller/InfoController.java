package com.example.final_exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {
    @GetMapping(value = "/cpu")
    public String cpu(Model model) {
        return "info/cpu";}

    @GetMapping(value = "/mainboard")
    public String mainboard (Model model){
        return "info/mainboard";
    }

    @GetMapping(value = "/ram")
    public String ram (Model model){
        return "info/ram";
    }

    @GetMapping(value = "/gpu")
    public String gpu (Model model){
        return "info/gpu";
    }

    @GetMapping(value = "/ssd")
    public String ssd (Model model){
        return "info/ssd";
    }

    @GetMapping(value = "/power")
    public String power (Model model){
        return "info/power";
    }

    @GetMapping(value = "/case")
    public String pc_case (Model model) {
        return "info/case";
    }

}

