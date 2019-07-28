package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateService {

    @Autowired
    private DateDao dateDao;

    @Cacheable("date")
    public LocalDateTime now(){
        return dateDao.now();
    }

}
