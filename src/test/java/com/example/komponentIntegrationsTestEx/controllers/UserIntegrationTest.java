package com.example.komponentIntegrationsTestEx.controllers;

import com.example.komponentIntegrationsTestEx.models.User;
import com.example.komponentIntegrationsTestEx.services.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

//Changes to app-test.properties file, so it uses the test database.
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback(value = true)
class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    //This is an Integration test and tests if creating and adding a new user to the database works. Also if we can get a users data with ID.

    @Test
    public void testCreateAndGetUserByEndpoints(){
        //Creates mew user
        User user = new User(null, "Niklas", "niklas@gmail.com");
        User savedUser = userService.createUser(user);

        //Triggeres the create new user endpoint
        ResponseEntity<User> postResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/user",
                savedUser,
                User.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        //Gets the userID of the created user
        Long userId = postResponse.getBody().getId();

        //Gets the user with the ID
        ResponseEntity<User> getResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/user/" + userId,
                User.class
        );
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        //Checks if the fetched data is correct
        assertEquals("Niklas", getResponse.getBody().getName());
    }
}