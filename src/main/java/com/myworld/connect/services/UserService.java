package com.myworld.connect.services;


import com.myworld.connect.DaoImpl.UserRepository;
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

    public User get(Integer id) {
        return userRepository.findOne(id);
    }

    public void update(User user, Integer userID) {
        userRepository.save(user);
    }

    public void delete(Integer userID) {
        userRepository.delete(userID);
    }
}
