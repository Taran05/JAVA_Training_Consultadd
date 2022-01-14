package com.task5b.controller;

import com.task5b.properties.MyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DispatchMessageContoller {

    @Autowired
    private MyProperties properties;

    @GetMapping("/message")
    public String message() {
        return "Hello " + properties.getName();
    }
}
