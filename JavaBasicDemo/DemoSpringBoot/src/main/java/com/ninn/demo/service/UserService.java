package com.ninn.demo.service;

import com.ninn.demo.domain.User;
import com.ninn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void insert(User user){
        userRepository.save(user);
    }

    public void delete(User user){

    }

    public void update(User user){

    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public User selectFirst(User user){
        return userRepository.findOne(user.getId());
    }



}
