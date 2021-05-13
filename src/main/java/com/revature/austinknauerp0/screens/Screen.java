package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.PeopleDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.services.CourseService;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.ScreenRouter;

import java.io.BufferedReader;

public abstract class Screen {

    public String name;
    public String route;
    protected UserDAO userDAO;
    protected CourseDAO courseDAO;
    protected PeopleDAO peopleDAO;
    protected CourseService courseService;
    protected UserService userService;
    protected ScreenRouter router;

    public Screen(UserService userService, ScreenRouter router) {
        this.userService = userService;
        this.router = router;
    }

    public Screen(UserDAO userDAO, CourseDAO courseDAO, ScreenRouter router) {
        this.userDAO = userDAO;
        this.courseDAO = courseDAO;
        this.router = router;
    }

    public Screen(UserDAO userDAO, UserService userService, ScreenRouter router) {
        this.userDAO = userDAO;
        this.userService = userService;
        this.router = router;
    }

    public Screen(CourseDAO courseDAO, PeopleDAO peopleDAO, ScreenRouter router) {
        this.courseDAO = courseDAO;
        this.peopleDAO = peopleDAO;
        this.router = router;
    }

    public Screen(CourseDAO courseDAO, ScreenRouter router) {
        this.courseDAO = courseDAO;
        this.router = router;
    }

    public Screen(CourseDAO courseDAO, CourseService courseService, ScreenRouter router) {
        this.courseDAO = courseDAO;
        this.courseService = courseService;
        this.router = router;
    }

    public Screen(UserService userService, PeopleDAO peopleDAO, ScreenRouter router) {
        this.peopleDAO = peopleDAO;
        this.userService = userService;
        this.router = router;
    }

    public Screen(CourseDAO courseDAO, PeopleDAO peopleDAO, CourseService courseService, ScreenRouter router) {
        this.courseDAO = courseDAO;
        this.peopleDAO = peopleDAO;
        this.courseService = courseService;
        this.router = router;
    }

    public abstract void render();



}
