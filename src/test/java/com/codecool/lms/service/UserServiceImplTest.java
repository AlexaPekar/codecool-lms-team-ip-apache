package com.codecool.lms.service;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.exception.UserNotFoundException;
import com.codecool.lms.exception.WrongPasswordException;
import com.codecool.lms.model.Day;
import com.codecool.lms.model.Mentor;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserServiceImpl userServiceImpl;
    User user1;
    User user2;
    User user3;
    Day day1;
    Day day2;

    @BeforeEach
    void setUp() throws UserAlreadyRegisteredException {
        userServiceImpl = new UserServiceImpl();
        user1 = userServiceImpl.createUser("kacsamesek@gmail.com", "Dagobert", "cash", "Student");
        user2 = userServiceImpl.createUser("iamagardener@freemail.hu", "Gardener", "palm3", "Student");
        user3 = userServiceImpl.createUser("ihavebeard@homo.com", "Conchita Wurst", "celeb88", "Student");
        List<Student> studentList1 = new ArrayList<>();
        List<Student> studentList2 = new ArrayList<>();
        studentList1.add((Student) user1);
        studentList2.add((Student) user2);
        studentList2.add((Student) user1);
        day1 = new Day(studentList1, "2017-09-13");
        day2 = new Day(studentList1, "2017-09-14");
        userServiceImpl.addDay(day1);
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


    @Test
    void createUser() {
        assertTrue(userServiceImpl.createUser("semmiertelme13@gmail.com", "Bence Farago", "lalalalala23", "Student") instanceof Student);
        assertTrue(userServiceImpl.createUser("semmiertelme13@gmail.com", "kancsal Jancsi", "jancsika23", "Mentor") instanceof Mentor);
    }

    @Test
    void findUserByName() {
        assertEquals(user1, userServiceImpl.findUserByName("Dagobert"));
        assertEquals("palm3", userServiceImpl.findUserByName("Gardener").getPassword());
    }

    @Test
    void addDay() {
        assertEquals(1, userServiceImpl.getDays().size());
        userServiceImpl.addDay(day2);
        assertEquals(2, userServiceImpl.getDays().size());

    }

    @Test
    void dayExist() {
        assertTrue(userServiceImpl.dayExist("2017-09-13"));
        assertFalse(userServiceImpl.dayExist("2017-09-22"));
    }

    @Test
    void findDayByDate() {
        assertEquals(day1, userServiceImpl.findDayByDate("2017-09-13"));
    }

    @Test
    void deleteUser() {
        assertEquals(2, userServiceImpl.getUsers().size());
        userServiceImpl.deleteUser("Dagobert");
        assertEquals(1, userServiceImpl.getUsers().size());
    }

    @Test
    void createAttendStudentList() {
        String[] strings = new String[1];
        strings[0] = "Dagobert";
        List<Student> students = userServiceImpl.createAttendStudentList(strings);
        assertEquals(1, students.size());

    }
}