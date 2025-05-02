package com.example.komponentIntegrationsTestEx.services;

import com.example.komponentIntegrationsTestEx.models.User;
import com.example.komponentIntegrationsTestEx.repositorys.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    //Mocks userRepository, that we later use in service
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    //Unit test
    //Tests if the findById function is working, if we can get user and its correct data with ID
    @Test
    public void testGetUserByIdReturnsUser(){
        //arrange
        User user = new User(1L, "anton", "anton@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //act
        User result = userService.getUserById(1L).orElse(null);

        //assert
        assertEquals("anton", result.getName());
        verify(userRepository).findById(1L);
    }

    //Unit test
    //Tests if the create new user function is working
    @Test
    public void testCreateUserReturnsUser(){
        //arrange
        User user = new User(1L, "anton", "anton@gmail.com");
        when(userRepository.save(user)).thenReturn(user);

        //act
        User result = userService.createUser(user);

        //assert
        assertEquals("anton@gmail.com", result.getEmail());
    }
}