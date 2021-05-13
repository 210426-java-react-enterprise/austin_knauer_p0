package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;
import com.revature.austinknauerp0.util.structures.Stack;

import java.io.BufferedReader;
import java.io.IOException;


public class Teacher extends Screen {

    public Teacher(CourseDAO courseDAO, ScreenRouter router) {
        super(courseDAO, router);
        this.name = "Teacher";
        this.route = "/teacher";
    }

    @Override
    public void render() {

        AppState app = Driver.getApp();
        // access student info for user
        String username = app.getUserInfo().getUsername();
        // to be replaced by a custom data structure
        Stack<Course> courses = courseDAO.selectAssociatedCourses(app.getUserInfo().getUserId(), "student");

        System.out.printf("Welcome %s!", username);
        System.out.println("Your courses:");

        if (courses.isEmpty()) {
            System.out.println("No registered courses.");
        }

        int[] courseIds = new int[courses.size()];

        try {
            for(int i = 0; i < courses.size(); i++) {
                Course course = courses.pop();
                courseIds[i] = course.getCourseId();
                System.out.printf("%s, %s", course.getCourseId(), course.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Options:");
        System.out.println("1) View course details.");
        System.out.println("2) Create a new course.");
        System.out.println("3) Directory");
        System.out.println("4) Account Info");
        System.out.println("5) Log Out");

        Integer selection = null;

        while (selection == null) {
            selection = userService.validateOptionSelection("1", "2", "3", "4", "5");
        }

        switch(selection) {
            case 1:
                Integer navigateTo = null;
                System.out.println("Please enter the course id you wish to see more details of.");

                while (navigateTo == null) {
                    navigateTo = courseService.validateCourseIdEntry(courseIds);
                }

               // router.route(navigateTo);
                break;
            case 2:
                router.route("/new-course");
                break;
            case 3:
                router.route("/directory");
                break;
            case 4:
                router.route("/account");
                break;
            case 5:
                // if this changes so that it stays on dashboard and doesn't go back to login this would have to reroute to login
                app.setAppUserFirstName("");
                app.setAppUserLastName("");
                app.setAppUserEmail("");
                app.setAppUserRole("");
                app.setAppUserUsername("");
                app.setAppUserPassword("");
                app.setAppUserId(-1);
                break;
            default:
                router.route("/Welcome");
        }


    }

}
