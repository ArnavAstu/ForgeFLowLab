package com.example.forgeflowlab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
//    @GetMapping("/hello") tells Spring that whenever a GET request comes to /hello, execute the hello() method
    public String hello(){
        return "Hello ForgeFlow";
    }

    @GetMapping("/hello/{name}")
    // @PathVariable allows to take the string from name
    public String hello (@PathVariable String name){
        return "hello " + name;
    }


}
