package com.maxsopilkov.redstorm.controllers;

import java.util.concurrent.atomic.AtomicLong;

import com.maxsopilkov.redstorm.entities.Calculate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/calculate")
    public Calculate calculate(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Calculate(counter.incrementAndGet(),
                String.format(template, name));
    }
}
