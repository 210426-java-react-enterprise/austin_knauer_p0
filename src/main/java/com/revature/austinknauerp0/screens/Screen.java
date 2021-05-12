package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.PeopleDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.services.CourseService;
import com.revature.austinknauerp0.services.UserService;

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

   public Screen(UserDAO userDAO, BufferedReader inputRead) {
        this.userDAO = userDAO;
        this.inputRead = inputRead;
   }

   public Screen(UserService userService, BufferedReader inputRead) {
       this.userService = userService;
       this.inputRead = inputRead;
   }



    public abstract void render();



}
