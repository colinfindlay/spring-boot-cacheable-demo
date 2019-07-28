package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DateController {

    @Autowired
    private DateService dateService;

    @RequestMapping("/now")
    public LocalDateTime now(){
        return dateService.now();
    }

}
