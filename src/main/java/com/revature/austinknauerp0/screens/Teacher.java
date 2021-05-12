package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Teacher extends Screen {

    public Teacher(CourseDAO courseDAO, BufferedReader inputRead, ScreenRouter router) {
        super(courseDAO, inputRead, router);
        this.name = "Teacher";
        this.route = "/teacher";
    }

    @Override
    public void render() {

        AppState app = Driver.getApp();
        // access student info for user
        String username = app.getUserInfo().getUsername();
        // to be replaced by a custom data structure
        List<Course> courses = courseDAO.selectAssociatedCourses(app.getUserInfo().getUserId(), "student");

        System.out.printf("Welcome %s!", username);
        System.out.println("Your courses:");

        if (courses.isEmpty()) {
            System.out.println("No registered courses.");
        }

        int[] courseIds = new int[courses.size()];

        for(int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            courseIds[i] = course.getCourseId();
            System.out.printf("%s, %s", course.getCourseId(), course.getName());
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
                    System.out.println("Please enter the course id you wish to see more details of.");
                    navigateTo = inputRead.readLine();

                    // an if statement inside a for loop inside a switch statement inside a try block? This is probably too messy, find another way.
                    // Is there a way to stay on the dashboard instead of going back to welcome screen?/
                    for (int i = 0; i < courseIds.length; i++) {
                        if (courseIds[i] == Integer.parseInt(navigateTo)) {
                            // need to make details route and inject the course info into it
                            router.route("/details");
                        } else if (i == courseIds.length - 1) {
                            System.out.println("Invalid course.");
                        }
                    }
                    break;
                case "2":
                    router.route("/new-course");
                    break;
                case "3":
                    router.route("/directory");
                    break;
                case "4":
                    router.route("/account");
                    break;
                case "5":
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
                    System.out.println("Invalid Entry.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
