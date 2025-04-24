package com.example.komponentIntegrationsTestEx.repositorys;

import com.example.komponentIntegrationsTestEx.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@Rollback(value = false)
class UserRepositoryJPATest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail(){
        User user = new User(null, "Anton", "anton@email.com");

        entityManager.persist(user);

        entityManager.persistAndFlush(user);

        Optional<User> foundUser = userRepository.findByEmail("anton@email.com");

        assertTrue(foundUser.isPresent());
        assertEquals("Anton", foundUser.get().getName());
    }

}