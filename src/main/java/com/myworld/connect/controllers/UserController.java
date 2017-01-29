package com.myworld.connect.controllers;

import com.myworld.connect.model.User;
import com.myworld.connect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(value = "/user/{emailID}", method = RequestMethod.GET)
    public User getDetails(@PathVariable("emailID") String emailID) {
        return userService.getByEmailID(emailID);
    }

    @RequestMapping(value = "/user/{emailID}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user, @PathVariable("emailID") String emailID) {
        userService.update(user, emailID);
    }

    @RequestMapping(value = "/user/{emailID}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("emailID") String emailID) {
        userService.delete(emailID);
    }


}
