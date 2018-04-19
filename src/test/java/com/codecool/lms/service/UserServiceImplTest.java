package com.codecool.lms.service;

import com.codecool.lms.exception.UserAlreadyRegisteredException;
import com.codecool.lms.model.Day;
import com.codecool.lms.model.Mentor;
import com.codecool.lms.model.Student;
import com.codecool.lms.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    UserServiceImpl userServiceImpl;
    User user1;
    User user2;
    User user3;
    Day day1;
    List<Student> studentList1 = new ArrayList<>();
    List<Student> studentList2 = new ArrayList<>();

    @BeforeEach
    void setUp() throws UserAlreadyRegisteredException, SQLException {
        userServiceImpl = new UserServiceImpl();
        user1 = userServiceImpl.createUser("kacsamesek@gmail.com", "Dagobert", "cash", "Student");
        user2 = userServiceImpl.createUser("iamagardener@freemail.hu", "Gardener", "palm3", "Student");
        user3 = userServiceImpl.createUser("ihavebeard@homo.com", "Conchita Wurst", "celeb88", "Student");
        List<Student> studentList1 = new ArrayList<>();
        List<Student> studentList2 = new ArrayList<>();
        studentList1.add((Student) user1);
        studentList2.add((Student) user2);
        studentList2.add((Student) user1);

        userServiceImpl.addDay("2017-09-13", studentList1);
        userServiceImpl.register("Dagobert","kacsamesek@gmail.com", "cash", "Student");
        userServiceImpl.register( "Conchita Wurst","ihavebeard@homo.com", "celeb88", "Student");

    }

    @Test
    void containsUser() {
        assertTrue(userServiceImpl.containsUser("kacsamesek@gmail.com"));
        assertFalse(userServiceImpl.containsUser("iamagardener@freemail.hu"));
    }

    @Test
    void register() throws UserAlreadyRegisteredException, SQLException {
        assertEquals(2, userServiceImpl.getUsers().size());
        userServiceImpl.register("ihavebeard@homo.com", "Conchita Wurst", "celeb88", "Student");
        assertEquals(3, userServiceImpl.getUsers().size());
    }

    @Test
    void createUser() {
        assertTrue(userServiceImpl.createUser("semmiertelme13@gmail.com", "Bence Farago", "lalalalala23", "Student") instanceof Student);
        assertTrue(userServiceImpl.createUser("semmiertelme13@gmail.com", "kancsal Jancsi", "jancsika23", "Mentor") instanceof Mentor);
    }

    @Test
    void findUserByName() {
        assertEquals("kacsamesek@gmail.com", userServiceImpl.findUserByName("Dagobert").getEmail());
        assertEquals("celeb88", userServiceImpl.findUserByName("Conchita Wurst").getPassword());
    }

    @Test
    void addDay() throws SQLException {
        assertEquals(1, userServiceImpl.getDays().size());
        userServiceImpl.addDay("2017-09-14", studentList1);
        assertEquals(2, userServiceImpl.getDays().size());

    }

    @Test
    void dayExist() {
        assertTrue(userServiceImpl.dayExist("2017-09-13"));
        assertFalse(userServiceImpl.dayExist("2017-09-22"));
    }

    @Test
    void findDayByDate() {
        assertEquals("2017-09-13", userServiceImpl.findDayByDate("2017-09-13").getDate());
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