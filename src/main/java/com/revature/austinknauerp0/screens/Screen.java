package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;

public abstract class Screen {

    public String name;
    public String route;
    private UserDAO userDAO;
    protected BufferedReader inputRead;

   public Screen(UserDAO userDAO, BufferedReader inputRead) {
        this.userDAO = userDAO;
        this.inputRead = inputRead;
    }

    public abstract void render();



}
