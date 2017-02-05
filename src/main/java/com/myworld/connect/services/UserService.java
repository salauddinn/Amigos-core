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
        User existingUser = getByEmailID(emailID);
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
    }

    public void delete(String emailID) throws UserNotFoundException {
        User existingUser = getByEmailID(emailID);
        userRepository.delete(existingUser.getUserID());
    }

    public User getByEmailID(String emailID) throws UserNotFoundException {
        User user = userRepository.findByEmailID(emailID);
        if (user == null) {
            throw new UserNotFoundException("User doesn't exist");
        }
        return user;
    }
}
