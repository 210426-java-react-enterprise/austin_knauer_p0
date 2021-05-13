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

   public Screen(UserDAO userDAO) {
        this.userDAO = userDAO;
   }

    public Screen(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    public Screen(ScreenRouter router) {
       this.router = router;
   }

    public Screen(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public Screen(CourseDAO courseDAO, PeopleDAO peopleDAO, CourseService courseService) {
        this.courseDAO = courseDAO;
        this.peopleDAO = peopleDAO;
        this.courseService = courseService;
    }

    public Screen(CourseDAO courseDAO, CourseService courseService) {
        this.courseDAO = courseDAO;
        this.courseService = courseService;
    }

    public Screen(UserDAO userDAO, UserService userService) {
        this.userDAO = userDAO;
        this.userService = userService;
    }

    public Screen(UserService userService) {
       this.userService = userService;
   }

   public Screen(UserDAO userDAO, ScreenRouter router) {
       this.userDAO = userDAO;
       this.router = router;
   }

   public Screen(CourseDAO courseDAO, ScreenRouter router) {
       this.courseDAO = courseDAO;
       this.router = router;
   }

    public Screen(CourseDAO courseDAO, PeopleDAO peopleDAO, ScreenRouter router) {
        this.courseDAO = courseDAO;
        this.peopleDAO = peopleDAO;
        this.router = router;
    }

    public Screen(UserService userService, PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
        this.userService = userService;
    }

    public abstract void render();



}
