package com.example.komponentIntegrationsTestEx.services;

import com.example.komponentIntegrationsTestEx.models.User;
import com.example.komponentIntegrationsTestEx.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id){
        return userRepository.findById(id);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }


    public void deleteUser(long id){
        userRepository.deleteById(id);
    }
}
