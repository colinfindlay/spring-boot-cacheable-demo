package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/*
1. Run this as a Spring Boot test so that it sets up the proxying via @EnableCaching on the application cache
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DateServiceTest {

    /*
    2. Replace the DateDao bean with a mock, this will allow us to verify that the number of calls made to the DateDao
     */
    @MockBean
    private DateDao dateDao;

    /*
    3. Autowire in the class under test that has been created by Spring
     */
    @Autowired
    private DateService dateService;

    /*
    4. Because we have replaced our DAO with a mock, we need to tell the Mock what to return when it is called
     */
    @Before
    public void setUp(){
        when(dateDao.now()).thenReturn(LocalDateTime.now());
    }

    @Test
    public void now() {

        // Call the service once, this will not have a cached value so the mock will be invoked
        LocalDateTime firstCallResult = dateService.now();

        // Call the service a second time, this will have a cached value so will not invoke our mock
        LocalDateTime secondCallResult = dateService.now();

        // Assert that the results from the first service call and the second one are the same
        assertThat(firstCallResult, equalTo(secondCallResult));

        // Verify that we have only invoked our DateDao once, as the second should have been serviced from the cache
        verify(dateDao, times(1)).now();

    }
}