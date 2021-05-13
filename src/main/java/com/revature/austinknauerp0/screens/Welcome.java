package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;

public class Welcome extends Screen {

    public Welcome(UserService userService, ScreenRouter router) {
        super(userService, router);
        this.name = "Welcome";
        this.route = "/welcome";
    }

    @Override
    public void render() {

        System.out.println("Welcome to EduGate, the all-in-one education portal.");
        System.out.println("----------------------------------------------------");

        String nextRoute = null;

        while(nextRoute == null) {
            nextRoute = this.userOptions();
        }

        router.route(nextRoute);

    }

    private String userOptions() {

        AppState app = Driver.getApp();

        // is doing this through a method a bad idea?
        System.out.println("1) Student Login");
        System.out.println("2) Faculty Login");
        System.out.println("3) Register");

        System.out.print("Your choice: ");

        switch(userService.validateOptionSelection("1", "2", "3")) {
            case 1:
                app.getUserInfo().setRole("student");
                return "/login";
            case 2:
                app.getUserInfo().setRole("teacher");
                return "/login";
            case 3:
                return "/register";
            default:
                System.out.println("Input not recognized. Please try again.");
                return null;

        }

    }

}
