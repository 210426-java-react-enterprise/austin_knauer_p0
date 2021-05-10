package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class Welcome extends Screen{

    public Welcome(UserDAO userDAO, BufferedReader inputRead) {
        super(userDAO, inputRead);
        this.name = "Welcome";
        this.route = "/welcome";
    }

    @Override
    public void render() {

        System.out.println("Welcome to EduGate, the all-in-one education portal.");
        System.out.println("----------------------------------------------------");

        boolean success = false;

        while(!success) {
            this.userOptions();
        }

    }

    private boolean userOptions() {
        // is doing this through a method a bad idea?
        System.out.println("> Student Login");
        System.out.println("> Faculty Login");
        System.out.println("> Register");

        System.out.print("Your choice:");
        try {
            switch(inputRead.readLine()) {
                case "Student Login":
                    // do something
                    break;
                case "Faculty Login":
                    // do something
                    break;
                case "Register":
                    // do something
                    break;
                default:
                    System.out.println("Input not recognized. Please try again.");
                    return false;
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; //don't forget to remove this
    }

}
