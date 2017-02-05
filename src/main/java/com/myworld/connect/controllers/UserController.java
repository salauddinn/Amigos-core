package com.myworld.connect.controllers;

import com.myworld.connect.model.User;
import com.myworld.connect.services.UserNotFoundException;
import com.myworld.connect.services.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getDetails(@RequestParam(value = "emailID") String emailID) throws UserNotFoundException {
            User user = userService.getByEmailID(emailID);
            return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user, @RequestParam(value = "emailID") String emailID) throws UserNotFoundException {
        userService.update(user, emailID);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        
    }
    
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestParam(value = "emailID") String emailID) throws UserNotFoundException {
        userService.delete(emailID);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
    
}
