/*package com.codecool.lms.service;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserServiceImpl userServiceImpl;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() throws UserAlreadyRegisteredException {
        userServiceImpl = new UserServiceImpl();
        user1 = new User("Dagobert", "kacsamesek@gmail.com", "cash");
        user2 = new User("Gardener", "iamagardener@freemail.hu", "palm3");
        user3 = new User("Conchita Wurst", "ihavebeard@homo.com", "celeb88");
        userServiceImpl.register(user1);
        userServiceImpl.register(user2);
    }

    @Test
    void containsUser() {
        assertTrue(userServiceImpl.containsUser("kacsamesek@gmail.com"));
        assertFalse(userServiceImpl.containsUser("ihavebeard@homo.com"));
    }

    @Test
    void register() throws UserAlreadyRegisteredException {
        assertEquals(2, userServiceImpl.getUsers().size());
        userServiceImpl.register(user3);
        assertEquals(3, userServiceImpl.getUsers().size());
    }

    @Test
    void findUserByEmail() throws UserNotFoundException, WrongPasswordException {
        assertEquals(user1, userServiceImpl.findUserByEmail("kacsamesek@gmail.com", "cash"));
        assertThrows(UserNotFoundException.class, () -> {
            userServiceImpl.findUserByEmail("ihavebeard@homo.com", "celeb88");
        });
        assertThrows(WrongPasswordException.class, () -> {
            userServiceImpl.findUserByEmail("kacsamesek@gmail.com", "123");
        });
    }
}*/