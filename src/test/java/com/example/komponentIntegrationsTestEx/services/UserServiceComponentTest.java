package com.example.komponentIntegrationsTestEx.services;

import com.example.komponentIntegrationsTestEx.models.User;
import com.example.komponentIntegrationsTestEx.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
//@Transactional
public class UserServiceComponentTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateAndFetchUser(){
        //arrange
        User user = new User(null, "Tratt", "tratt@gmail.com");

        //act
        User savedUser = userService.createUser(user);
        User fetchedUser = userService.getUserById(savedUser.getId()).orElse(null);

        //assert
        assertEquals("Tratt", fetchedUser.getName());
    }
}
