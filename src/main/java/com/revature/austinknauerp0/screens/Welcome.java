package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;

public class Welcome extends Screen{

    public Welcome(BufferedReader inputRead, ScreenRouter router) {
        super(inputRead, router);
        this.name = "Welcome";
        this.route = "/welcome";
    }

    @Override
    public void render() {

        System.out.println("Welcome to EduGate, the all-in-one education portal.");
        System.out.println("----------------------------------------------------");

        boolean success = false;

        while(!success) {
            success = this.userOptions();
        }

    }

    private boolean userOptions() {

        AppState app = Driver.getApp();

        // is doing this through a method a bad idea?
        System.out.println("1) Student Login");
        System.out.println("2) Faculty Login");
        System.out.println("3) Register");

        System.out.print("Your choice:");
        try {
            switch(inputRead.readLine()) {
                case "Student Login":
                    app.getUserInfo().setRole("student");
                    router.route("/login");
                    break;
                case "Faculty Login":
                    app.getUserInfo().setRole("teacher");
                    router.route("/login");
                    break;
                case "Register":
                    router.route("/register");
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
