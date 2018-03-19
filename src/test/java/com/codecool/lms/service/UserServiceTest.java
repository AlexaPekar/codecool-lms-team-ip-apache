package com.codecool.lms.service;

import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        user1 = new User("Dagobert", "kacsamesek@gmail.com", "cash");
        user2 = new User("Gardener", "iamagardener@freemail.hu", "palm3");
        user3 = new User("Conchita Wurst", "Ihavebeard@homo.com", "celeb88");
        userService.register(user1);
        userService.register(user2);
    }

    @Test
    void containsUser() {
        assertTrue(userService.containsUser("kacsamesek@gmail.com"));
        assertFalse(userService.containsUser("ihavebeard@homo.com"));
    }

    @Test
    void register() {
        assertEquals(2, userService.getUsers().size());
        userService.register(user3);
        assertEquals(3, userService.getUsers().size());
    }

    @Test
    void setCurrentUser() {
        userService.setCurrentUser(user1);
        assertEquals("Dagobert", UserService.getCurrentUser().getName());

    }

    @Test
    void findUserByEmail() throws UserNotFoundException, WrongPasswordException {
        assertEquals(user1, userService.findUserByEmail("kacsamesek@gmail.com", "cash"));
        assertThrows(UserNotFoundException.class, () -> {
            userService.findUserByEmail("ihavebeard@homo.com", "celeb88");
        });
        assertThrows(WrongPasswordException.class, () -> {
            userService.findUserByEmail("kacsamesek@gmail.com", "123");
        });
    }
}