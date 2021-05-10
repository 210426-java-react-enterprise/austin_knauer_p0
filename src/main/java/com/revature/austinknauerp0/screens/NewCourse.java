package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class NewCourse extends Screen {

    public NewCourse(UserDAO userDAO, BufferedReader inputRead) {
        super(userDAO, inputRead);
        this.name = "New Course";
        this.route = "/new-course";
    }

    @Override
    public void render() {

        String name;
        String description;
        String credits;

        System.out.println("Great, let's get your course set up. Please provide the following info.");
        // add a way to exit out back to dashboard

        try {

            System.out.print("Course Name: ");
            name = inputRead.readLine();
            System.out.print("Course Description: ");
            description = inputRead.readLine();
            System.out.print("Number of credit hours: ");
            credits = inputRead.readLine();
            System.out.print("Create course? (Y/N): ");

            // on y entered info needs to be validated then uploaded to database

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
