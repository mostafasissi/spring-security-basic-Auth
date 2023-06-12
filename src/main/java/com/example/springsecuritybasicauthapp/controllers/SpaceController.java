package com.example.springsecuritybasicauthapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaceController {
    @GetMapping("/navigation/destination")
    public String currentDistination(){
        return "MARS" ;
    }
    @GetMapping("/cantina/menu/today")
    public  String menu(){
        return "Tagin ...." ;
    }
}