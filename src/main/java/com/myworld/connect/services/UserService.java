package com.myworld.connect.services;


import com.myworld.connect.repositories.UserRepository;
import com.myworld.connect.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(User user, String emailID) {
        User existingUser = userRepository.findByEmailID(emailID);
        if (existingUser != null)
            existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
    }

    public void delete(String emailID) {
        User existingUser = userRepository.findByEmailID(emailID);
        if (existingUser != null)
            userRepository.delete(existingUser.getUserID());
    }

    public User getByEmailID(String emailID) {
        return userRepository.findByEmailID(emailID);
    }
}
