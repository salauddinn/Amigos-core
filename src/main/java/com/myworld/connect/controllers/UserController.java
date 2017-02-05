package com.myworld.connect.controllers;

import com.myworld.connect.model.User;
import com.myworld.connect.services.UserNotFoundException;
import com.myworld.connect.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    public ResponseEntity<User> getDetails(@PathVariable("emailID") String emailID) {
        try {
            User user = userService.getByEmailID(emailID);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/user/{emailID:.+}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable("emailID") String emailID) {
        try {
            userService.update(user, emailID);
            return new ResponseEntity<>("Details updated for " + user.getEmailID(), HttpStatus.ACCEPTED);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/user/{emailID:.+}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("emailID") String emailID) throws UserNotFoundException {
        userService.delete(emailID);
    }
    
}
