package com.codecool.lms.service;

import com.codecool.lms.exception.PageNotFoundException;
import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.Page;
import com.codecool.lms.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getCurrentUser();
    boolean containsUser(String email);
    void register(User user) throws UserAlreadyRegisteredException;
    void setCurrentUser(User user);
    User findUserByEmail(String email, String password) throws UserNotFoundException, WrongPasswordException;
    User createUser(String email, String name, String password, String type);
}
