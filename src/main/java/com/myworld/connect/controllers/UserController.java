package com.myworld.connect.controllers;

import com.myworld.connect.model.User;
import com.myworld.connect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(value = "/user/{userID}", method = RequestMethod.GET)
    public User getDetails(@PathVariable("userID") Integer userID) {
        return userService.get(userID);
    }

    @RequestMapping(value = "/user/{userID}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user, @PathParam("userID") Integer id) {
        userService.update(user, id);
    }


    @RequestMapping(value = "/user/{userID}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("userID") Integer userID) {
        userService.delete(userID);
    }


}
