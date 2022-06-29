package com.example.hikari.controller;

import com.example.hikari.beans.User;
import com.example.hikari.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/hikari/api/v1/user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Get all users details");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "This is custom header value");

        return new ResponseEntity<>(userService.getAllUsers(), headers, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        logger.info("Get "+ id +" user details");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "This is custom header value");

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

}
