package com.github.fabioper.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/api")
    public ExampleResponse example() {
        return new ExampleResponse();
    }

    public class ExampleResponse {
        public String message = "Hello world";
    }
}
