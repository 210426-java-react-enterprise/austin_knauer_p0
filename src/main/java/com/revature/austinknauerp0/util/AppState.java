package com.revature.austinknauerp0.util;

import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.PeopleDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.screens.*;
import com.revature.austinknauerp0.services.CourseService;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.structures.ArrayList;
import com.revature.austinknauerp0.util.structures.Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    // should bufferedreader be final?
    private BufferedReader inputRead;
    private final UserDAO userDAO;
    private final CourseDAO courseDAO;
    private final PeopleDAO peopleDAO;
    private AppUser appUser;
    private Course currentCourse;
    private ArrayList<Course> associatedCourses;
    private boolean isRunning;
    private ScreenRouter router;
    private UserService userService;
    private CourseService courseService;
    private Welcome start;
    // still needs user service and screen router

    public AppState() {
        System.out.println("Application Starting...");
        isRunning = true;

        inputRead = new BufferedReader(new InputStreamReader(System.in));

        userDAO = new UserDAO();
        courseDAO = new CourseDAO();
        peopleDAO = new PeopleDAO();

        userService = new UserService(userDAO, inputRead);
        courseService = new CourseService(courseDAO, inputRead);

        appUser = new AppUser();
        currentCourse = new Course();
        associatedCourses = new ArrayList<Course>();

        start = new Welcome(router);
        router = new ScreenRouter(start);

        router.add(start)
                .add(new Register(userService, router))
                .add(new Login(userDAO, courseDAO, router))
                .add(new Account(userDAO, userService, router))
                .add(new Student(courseDAO, peopleDAO, router))
                .add(new Teacher(courseDAO, router))
                .add(new NewCourse(courseDAO, courseService, router))
                .add(new Directory(userService, peopleDAO, router))
                .add(new CourseRegistration(courseDAO, peopleDAO, courseService, router));
        System.out.println("Application Ready!");
    }

    public void setIsRunning(boolean bool) {
        isRunning = bool;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public ScreenRouter getScreenRouter() {
        return router;
    }

    public AppUser getUserInfo() {
        return this.appUser;
    }

    public Course getCurrentCourse() { return this.currentCourse; }

    public void setCurrentCourse(Course course) { currentCourse = course; }

    public ArrayList<Course> getCourseList() { return this.associatedCourses; }

    public void setCourseList(Stack<Course> courses) throws Exception {
        for (int i = 0; i < courses.size(); i++) {
            this.associatedCourses.add(courses.pop());
        }
    }

    public void emptyCourseList() {
        this.associatedCourses.empty();
    }

    public void setAppUserUsername(String username) {
        appUser.setUsername(username);
    }

    public void setAppUserFirstName(String firstName) {
        appUser.setFirstName(firstName);
    }

    public void setAppUserLastName(String lastName) {
        appUser.setLastName(lastName);
    }

    public void setAppUserPassword(String password) {
        appUser.setPassword(password);
    }

    public void setAppUserRole(String role) {
        appUser.setRole(role);
    }

    public void setAppUserEmail(String email) {
        appUser.setEmail(email);
    }

    public void setAppUserId(int id) {
        appUser.setUserId(id);
    }



}
