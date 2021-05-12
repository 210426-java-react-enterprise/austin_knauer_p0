package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.services.CourseService;
import com.revature.austinknauerp0.util.AppState;

import java.io.BufferedReader;
import java.io.IOException;

public class NewCourse extends Screen {

    public NewCourse(CourseDAO courseDAO, CourseService courseService, BufferedReader inputRead) {
        super(courseDAO, courseService, inputRead);
        this.name = "New Course";
        this.route = "/new-course";
    }

    @Override
    public void render() {

        // is there a way to inject this instead of summoning the appstate object?
        AppState app = Driver.getApp();

        String name = null;
        String description = null;
        Integer credits = null;

        System.out.println("Great, let's get your course set up. Please provide the following info.");
        // add a way to exit out back to dashboard

        try {

            while(name == null) {
                System.out.print("Course Name: ");
                String n = inputRead.readLine();
                if (courseService.validateName(n)) {
                    name = n;
                } else {
                    System.out.println("Invalid entry. Course name must be between 1 and 100 characters.");
                }
            }

            while(description == null) {
                System.out.print("Course Description: ");
                String ds = inputRead.readLine();
                if (courseService.validateDescription(ds)) {
                    description = ds;
                } else {
                    System.out.println("Invalid entry. Descriptions must be less than 1000 characters.");
                }
            }

            while(credits == null) {
                System.out.print("Credit Hourrs: ");
                        String ch = inputRead.readLine();
                        if (courseService.validateCredits(Integer.parseInt(ch))) {
                            credits = Integer.parseInt(ch);
                        } else {
                            System.out.println("Invalid entry. Courses can have a minimum of 1 and maximum of 8 credit hours.");
                        }
                    }

            System.out.print("Create course? (Y/N): ");
            String proceed = inputRead.readLine();
            if (proceed == "Y" || proceed == "y") {
                boolean success = courseDAO.insertCourse(name, description, credits, app.getUserInfo().getUserId());
                if (success) {
                    System.out.println("Course created successfully!");
                }
            }


            // on y entered info needs to be validated then uploaded to database

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
