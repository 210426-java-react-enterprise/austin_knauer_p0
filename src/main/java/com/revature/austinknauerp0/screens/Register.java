package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class Register extends Screen {

    public Register(UserDAO userDAO, BufferedReader inputRead) {
        super(userDAO, inputRead);
        this.name = "Register";
        this.route = "/register";
    }

    @Override
    public void render() {

        String firstName;
        String lastName;
        String username;
        String password;
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
            // information needs to be validated
            System.out.print("First Name: ");
            firstName = inputRead.readLine();
            System.out.print("Last Name: ");
            lastName = inputRead.readLine();
            // constraints of username and password need to be given
            System.out.print("Username: ");
            username = inputRead.readLine();
            System.out.print("Password: ");
            password = inputRead.readLine();
            if (role == "teacher") {
                System.out.print("Access Key:");
                // handle access key
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        // userDAO needs to update records with info
        // consider how exceptions are handled more

    }

}
