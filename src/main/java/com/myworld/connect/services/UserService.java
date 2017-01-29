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

    public void update(User user, String emailID) throws UserNotFoundException{
        User existingUser = userRepository.findByEmailID(emailID);
        try {
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
        }
        catch (NullPointerException ex) {
            throw new UserNotFoundException("User doesn't exist");
        }
    }

    public void delete(String emailID) throws UserNotFoundException {
        User existingUser = userRepository.findByEmailID(emailID);
        try {
            userRepository.delete(existingUser.getUserID());
        }
        catch (NullPointerException ex) {
            throw new UserNotFoundException("User doesn't exist");
        }
    }

    public User getByEmailID(String emailID) {
        return userRepository.findByEmailID(emailID);
    }
}
