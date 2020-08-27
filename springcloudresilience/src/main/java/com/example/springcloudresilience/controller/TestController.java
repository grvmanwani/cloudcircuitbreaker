package com.example.springcloudresilience.controller;

import com.example.springcloudresilience.service.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private Test test;

    @GetMapping("/data")
    private ResponseEntity<Integer> getData(){
        return ResponseEntity.ok(test.getData());
    }
}
