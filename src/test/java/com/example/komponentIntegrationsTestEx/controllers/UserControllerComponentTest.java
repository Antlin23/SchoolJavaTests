package com.example.komponentIntegrationsTestEx.controllers;

import com.example.komponentIntegrationsTestEx.models.User;
import com.example.komponentIntegrationsTestEx.repositorys.UserRepository;
import com.example.komponentIntegrationsTestEx.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ActiveProfiles("test")
class UserControllerComponentTest {

    private UserRepository mockRepo;
    private UserService userService;
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockRepo = mock(UserRepository.class);
        userService = new UserService(mockRepo);
        userController = new UserController(userService);
    }

    @AfterEach
    void tearDown() {
        mockRepo = null;
        userService = null;
        userController = null;
    }

    //Component test
    //Tests if fetching a users data with userID works.
    @Test
    void getUserShouldReturnUser() {
        // Arrange
        //Creates user
        User user = new User(1L, "Anton", "Anton@email.com");
        when(mockRepo.findById(1L)).thenReturn(Optional.of(user));

        // Act
        //Gets the user with the ID
        Optional<User> result = userController.getUserById(1L);

        // Assert
        //Checks the data is correct
        assertEquals("Anton", result.get().getName());
        assertEquals("Anton@email.com", result.get().getEmail());
    }
}
