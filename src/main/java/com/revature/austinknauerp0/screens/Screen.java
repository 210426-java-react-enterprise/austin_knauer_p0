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
    protected BufferedReader inputRead;
    protected ScreenRouter router;

   public Screen(UserDAO userDAO, BufferedReader inputRead) {
        this.userDAO = userDAO;
        this.inputRead = inputRead;
   }

    public Screen(PeopleDAO peopleDAO, BufferedReader inputRead) {
        this.peopleDAO = peopleDAO;
        this.inputRead = inputRead;
    }

    public Screen(BufferedReader inputRead, ScreenRouter router) {
       this.inputRead = inputRead;
       this.router = router;
   }

    public Screen(CourseDAO courseDAO, BufferedReader inputRead) {
        this.courseDAO = courseDAO;
        this.inputRead = inputRead;
    }

    public Screen(CourseDAO courseDAO, PeopleDAO peopleDAO, CourseService courseService, BufferedReader inputRead) {
        this.courseDAO = courseDAO;
        this.peopleDAO = peopleDAO;
        this.courseService = courseService;
        this.inputRead = inputRead;
    }

    public Screen(CourseDAO courseDAO, CourseService courseService, BufferedReader inputRead) {
        this.courseDAO = courseDAO;
        this.courseService = courseService;
        this.inputRead = inputRead;
    }

    public Screen(UserDAO userDAO, UserService userService, BufferedReader inputRead) {
        this.userDAO = userDAO;
        this.userService = userService;
        this.inputRead = inputRead;
    }

    public Screen(UserService userService, BufferedReader inputRead) {
       this.userService = userService;
       this.inputRead = inputRead;
   }

   public Screen(UserDAO userDAO, BufferedReader inputRead, ScreenRouter router) {
       this.userDAO = userDAO;
       this.inputRead = inputRead;
       this.router = router;
   }

   public Screen(CourseDAO courseDAO, BufferedReader inputRead, ScreenRouter router) {
       this.courseDAO = courseDAO;
       this.inputRead = inputRead;
       this.router = router;
   }

    public Screen(CourseDAO courseDAO, PeopleDAO peopleDAO, BufferedReader inputRead, ScreenRouter router) {
        this.courseDAO = courseDAO;
        this.peopleDAO = peopleDAO;
        this.inputRead = inputRead;
        this.router = router;
    }

    public abstract void render();



}
