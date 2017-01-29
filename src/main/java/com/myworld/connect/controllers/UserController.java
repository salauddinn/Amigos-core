package com.myworld.connect.controllers;

import com.myworld.connect.model.User;
import com.myworld.connect.services.UserNotFoundException;
import com.myworld.connect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(value = "/user/{emailID:.+}", method = RequestMethod.GET)
    public User getDetails(@PathVariable("emailID") String emailID) {
        return userService.getByEmailID(emailID);
    }

    @RequestMapping(value = "/user/{emailID:.+}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(@RequestBody User user, @PathVariable("emailID") String emailID) throws UserNotFoundException {
        userService.update(user, emailID);
    }

    @RequestMapping(value = "/user/{emailID:.+}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("emailID") String emailID) throws UserNotFoundException {
        userService.delete(emailID);
    }


}
