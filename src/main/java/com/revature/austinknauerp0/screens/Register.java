package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;

public class Register extends Screen {

    public Register(UserService userService, ScreenRouter router) {
        super(userService, router);
        this.name = "Register";
        this.route = "/register";
    }


    @Override
    public void render() {

        String firstName = null;
        String lastName = null;
        String username = null;
        String password = null;
        String email = null;
        String role = null;
        AppState app = Driver.getApp();

        System.out.println("Great, let's get started! Enter your information below.");
        System.out.println("Would you like to Register as a student or instructor?");
        System.out.println("1) Student");
        System.out.println("2) Teacher");

        Integer entry = null;

        while (entry == null) {
           entry = userService.validateOptionSelection("1", "2");
        }

        switch (entry) {
            case 1:
                role = "student";
                break;
            case 2:
                role = "teacher";
                break;
                // throw exception if for some reason it isn't one of those two?
        }

        while(firstName == null) {
            System.out.print("First Name: ");
            firstName = userService.validateName();
        }

        while(lastName == null) {
            System.out.print("Last Name: ");
            lastName = userService.validateName();
        }

        while(username == null) {
            System.out.print("Username: ");
            username = userService.validateUsername();
            if (username != null) {
                username = !userService.usernameAvailable(username) ? null : username;
            }
        }

        while(password == null) {
            System.out.print("Password: ");
            password = userService.validatePassword();
        }

        while(email == null) {
            System.out.print("Email: ");
            email = userService.validateEmail();
            if (email != null) {
                email = !userService.emailAvailable(email) ? null : email;
            }
        }

        while(firstName == null) {
            System.out.print("First Name: ");
            firstName = userService.validateName();
        }

        if (role == "teacher") {
            System.out.print("Access Key: ");
            // handle access key
        }

        Integer continueReg = null;

        System.out.println("Confirm Registration? Enter 1 for YES or 2 to EXIT back to welcome screen.");
        while(continueReg == null) {
            userService.validateOptionSelection("1", "2");
        }

        if (continueReg == 2) {
            router.route("/welcome");
        } else {
            boolean success = userService.validateUserAndSave(username, password, firstName, lastName, email, role);
            if (success) {
                // not the best way of doing this, need a way for both inserts to be verified and only happen if both work?
                boolean secondSuccess = role == "teacher" ? peopleDAO.insertTeacher(app.getUserInfo().getUserId()) : peopleDAO.insertStudent(app.getUserInfo().getUserId());
                if (secondSuccess)
                    System.out.println("Registration successful! Redirecting to welcome page.");
                else
                    System.out.println("Registration failed. Please try again later.");

                String nextRoute = role == "teacher" ? "teacher" : "student";
                router.route(nextRoute);
            }
        }

       // NEED TO ADD BETTER WAY TO EXIT OUT IN THE MIDDLE OF THIS PROCESS


        router.route("/welcome");
    }

}
