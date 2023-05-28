package com.zadeon.customermanagement.service;

import com.zadeon.customermanagement.entity.User;
import com.zadeon.customermanagement.respository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepo;

    public User registerUser(String login, String password, String email){
        if(login == null && password == null){
            return null;
        }
        else{
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            return userRepo.save(user);
        }
    }

    public User authenticate(String login, String password){
        return userRepo.findByLoginAndPassword(login, password).orElse(null);
    }
}
