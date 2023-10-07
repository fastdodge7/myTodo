package com.toyproject.mytodo.Service;

import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    @Transactional
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public List<User> findByName(String name){
        return userRepository.findByName(name);
    }

    @Transactional
    public List<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public Long deleteUserById(Long id){
        userRepository.deleteById(id);
        return id;
    }
}
