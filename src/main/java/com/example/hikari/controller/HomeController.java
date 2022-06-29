package com.example.hikari.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    @RequestMapping("/")
    public ResponseEntity<String> getIndexPage() {
        logger.info("Get index");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "This is custom header value");

        return new ResponseEntity<>("Hello", headers, HttpStatus.OK);
    }

}
