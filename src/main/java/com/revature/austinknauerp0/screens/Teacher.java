package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class Teacher extends Screen {

    public Teacher(UserDAO userDAO, BufferedReader inputRead) {
        super(userDAO, inputRead);
        this.name = "Teacher";
        this.route = "/teacher";
    }

    @Override
    public void render() {

        // access teacher info for user
        // may change to first name instead of username
        String username = null;
        // to be replaced by a custom data structure
        String[] courses = new String[1];

        System.out.printf("Welcome %s!", username);
        System.out.println("Your courses:");

        if (courses[0] == null) {
            System.out.println("No courses found.");
        }

        for(String course : courses) {
            System.out.println(course);
        }

        System.out.println("Options:");
        System.out.println("1) View course details.");
        System.out.println("2) Create a new course.");
        System.out.println("3) Directory");
        System.out.println("4) Account Info");
        System.out.println("5) Log Out");

        try {
            switch(inputRead.readLine()) {
                case "1":
                    String navigateTo;
                    System.out.println("Please enter the name of the course you wish to see more details of.");
                    navigateTo = inputRead.readLine();
                    // ensure that entered string is actually a course and navigate to details route with that class's info
                    break;
                case "2":
                    // navigate to NewCourse screen
                    break;
                case "3":
                    // navigate to Directory screen
                    break;
                case "4":
                    // navigate to Account screen
                    break;
                case "5":
                    // navigate to login screen, remove user info
                    break;
                default:
                    System.out.println("Invalid Entry.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
