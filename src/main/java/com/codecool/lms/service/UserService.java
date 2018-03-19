package com.codecool.lms.service;

import com.codecool.lms.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }
}