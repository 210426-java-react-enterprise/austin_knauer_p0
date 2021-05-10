package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class Login extends Screen {

    public Login(UserDAO userDAO, BufferedReader inputRead) {
        super(userDAO, inputRead);
        this.name = "Login";
        this.route = "/login";
    }

    @Override
    public void render() {

        String username;
        String password;

        System.out.println("Enter your credentials to login.");

        try {
            // info needs to be validated against database
            // store password in properties file?
            System.out.print("Username: ");
            username = inputRead.readLine();
            System.out.println("Password: ");
            password = inputRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
