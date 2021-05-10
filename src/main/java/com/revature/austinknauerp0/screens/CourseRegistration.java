package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class CourseRegistration extends Screen {

    public CourseRegistration(UserDAO userDAO, BufferedReader inputRead) {
        super(userDAO, inputRead);
        this.name = "Course Registration";
        this.route = "/course-registration";
    }

    @Override
    public void render() {

        // access list of all available courses minus currently registered ones
        // to be replaced by a custom data structure
        String[] courses = new String[1];
        String toRegister;

        if (courses[0] == null) {
            System.out.println("No available courses.");
        }

        for(String course : courses) {
            System.out.println(course);
        }

        System.out.println("Options: ");
        System.out.println("1) Register");
        System.out.println("2) Back to Dashboard");

        try {
            if (inputRead.readLine() == "1") {
                System.out.print("Enter the id of the course you'd like to register for: ");
                toRegister = inputRead.readLine();
                // validate that user has enough available credits to register then update DB
            } else if (inputRead.readLine() == "2") {
                // navigate back to dashboard
            } else {
                System.out.println("Invalid Entry.");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
