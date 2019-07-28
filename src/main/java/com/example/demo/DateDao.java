package com.example.demo;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateDao {

    public LocalDateTime now(){
        System.out.println("What time is it now?");
        return LocalDateTime.now();
    }

}
