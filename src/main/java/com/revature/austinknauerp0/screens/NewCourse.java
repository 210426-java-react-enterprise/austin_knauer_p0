package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.services.CourseService;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;
import com.revature.austinknauerp0.util.structures.Stack;

public class NewCourse extends Screen {

    public NewCourse(CourseDAO courseDAO, CourseService courseService, ScreenRouter router) {
        super(courseDAO, courseService, router);
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

        while(name == null) {
            System.out.print("Course Name: ");
            name = courseService.validateName();
        }

        while(description == null) {
            System.out.print("Course Description: ");
            description = courseService.validateDescription();
        }

        while(credits == null) {
            System.out.print("Credit Hours: ");
            credits = courseService.validateCredits();
        }

        Integer continueReg = null;

        System.out.println("Confirm create? Enter 1 for YES or 2 to EXIT back to your dashboard.");
        while(continueReg == null) {
            courseService.validateOptionSelection("1", "2");
        }

        if (continueReg == 2) {
            router.route("/teacher");
        }

        boolean success = courseDAO.insertCourse(name, description, credits, app.getUserInfo().getUserId());
        if (success) {
            System.out.println("Course created successfully!");
            Stack<Course> courses = courseDAO.selectAssociatedCourses(app.getUserInfo().getUserId(), "teacher");

            try {
                app.setCourseList(courses);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Creation failed. Please try again later.");
        }

        router.route("/teacher");
    }
}
