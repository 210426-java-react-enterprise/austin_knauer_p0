package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class Register extends Screen {

    public Register(UserService userService, BufferedReader inputRead) {
        super(userService, inputRead);
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

        System.out.println("Great, let's get started! Enter your information below.");
        System.out.println("Would you like to Register as a student or instructor?");
        System.out.println("1) Student");
        System.out.println("2) Teacher");

        // is this while loop bad practice/could it lead to overflow?

        while (role == null) {
            try {
                switch(inputRead.readLine()) {
                    case "1":
                        role = "student";
                        break;
                    case "2":
                        role = "teacher";
                        break;
                    default:
                        role = null;

                };
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {

            while(firstName == null) {
                System.out.print("First Name: ");
                String fn = inputRead.readLine();
                if (userService.validateName(fn)) {
                    firstName = fn;
                } else {
                    System.out.println("Invalid entry. Names can only contain letters A-Z and must be between 1 and 30 characters.");
                }
            }


            while(lastName == null) {
                System.out.print("Last Name: ");
                String ln = inputRead.readLine();
                if (userService.validateName(ln)) {
                    lastName = ln;
                } else {
                    System.out.println("Invalid entry. Names can only contain letters A-Z and must be between 1 and 30 characters.");
                }
            }


            while(username == null) {
                System.out.print("Username: ");
                String un = inputRead.readLine();
                if (!userService.validateUsername(un)) {
                    System.out.println("Invalid entry. Usernames must not contain any spaces and must be between 3 and 30 characters.");
                } else if (!userService.emailAvailable(un)) {
                    System.out.println("Username unavailable. Please try another username.");
                } else {
                    username = un;
                }
            }

            while(password == null) {
                System.out.print("Password: ");
                String pw = inputRead.readLine();
                if (userService.validatePassword(pw)) {
                    password = pw;
                } else {
                    System.out.println("Invalid entry. Passwords cannot contain white space, must be between 6 and 30 characters, and must contain one number and one special character (!@#$%^).");
                }
            }

            while(email == null) {
                System.out.print("Email: ");
                String e = inputRead.readLine();
                if (!userService.validateEmail(e)) {
                    System.out.println("Invalid entry. Emails must contain no white space and be between 6 and 100 characters.");
                } else if (!userService.emailAvailable(e)) {
                    System.out.println("Email already associated with an account. Please try another email.");
                } else {
                    email = e;
                }
            }

            if (role == "teacher") {
                System.out.print("Access Key: ");
                // handle access key
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

       // NEED TO ADD WAY TO EXIT OUT IN THE MIDDLE OF THIS PROCESS
        // consider how exceptions are handled more
        boolean success = userService.validateUserAndSave(firstName, lastName, username, password, email, role);
        if (success) {
            System.out.println("Registration successful! Redirecting to welcome page.");
        } else {
            System.out.println("Registration failed. Please try again later.");
        }
    }

}
