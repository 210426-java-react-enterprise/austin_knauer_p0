package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.PeopleDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;
import com.revature.austinknauerp0.util.structures.Stack;

import java.io.BufferedReader;
import java.io.IOException;

public class Student extends Screen {

    public Student(CourseDAO courseDAO, PeopleDAO peopleDAO, ScreenRouter router) {
        super(courseDAO, peopleDAO, router);
        this.name = "Student";
        this.route = "/student";
    }

    @Override
    public void render() {

        AppState app = Driver.getApp();
        // access student info for user
        String username = app.getUserInfo().getUsername();
        // should this fetch the list from the DB every time the page is rendered?
        Stack<Course> courses = courseDAO.selectAssociatedCourses(app.getUserInfo().getUserId(), "student");

        System.out.printf("Welcome %s!", username);
        System.out.println("Your courses:");

        if (courses.isEmpty()) {
            System.out.println("No registered courses.");
        }

        int[] courseIds = new int[courses.size()];


        try {
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.pop();
                courseIds[i] = course.getCourseId();
                System.out.printf("%s, %s, %s", course.getCourseId(), course.getName(), peopleDAO.selectTeacher(course.getTeacherId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Options:");
        System.out.println("1) View course details.");
        System.out.println("2) Register for a course.");
        System.out.println("3) Directory");
        System.out.println("4) Account Info");
        System.out.println("5) Log Out");

        Integer selection = null;

        while (selection == null) {
            selection = userService.validateOptionSelection("1", "2", "3", "4", "5");
        }

        switch (selection) {
            case 1:
                Integer navigateTo = null;
                System.out.println("Please enter the course id you wish to see more details of.");

                while (navigateTo == null) {
                    navigateTo = courseService.validateCourseIdEntry(courseIds);
                }

                // router.route(navigateTo);
                break;
            case 2:
                // find a way to pass in currently enrolled courses?
                router.route("/course-registration");
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
            default:
                router.route("/welcome");
        }
    }


}
