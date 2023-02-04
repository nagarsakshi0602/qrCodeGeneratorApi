package com.example.springdataDemo.controller;

import com.example.springdataDemo.model.User;
import com.example.springdataDemo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<User> getAllUsers(@PathVariable Long id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/personByName/{name}", method = RequestMethod.GET)
    public List<User> getPersoneByName(@PathVariable String name) {
        return userService.findByName(name);
    }
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAllPersons();
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public HttpStatus deletePersnone(@PathVariable Long id) {
        userService.deletePerson(id);
        return HttpStatus.NO_CONTENT;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public HttpStatus insertPersone(@RequestBody User user) {
        return userService.addPerson(user) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

    @RequestMapping(value = "/person", method = RequestMethod.PUT)
    public HttpStatus updatePerson(@RequestBody User user) {
        return userService.updatePerson(user) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }
}
