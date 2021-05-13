package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;
import com.revature.austinknauerp0.util.structures.Stack;

public class Login extends Screen {

    public Login(UserDAO userDAO, CourseDAO courseDAO, UserService userService, ScreenRouter router) {
        super(userDAO, courseDAO, userService, router);
        this.name = "Login";
        this.route = "/login";
    }

    @Override
    public void render() {

        AppState app = Driver.getApp();

        String username = null;
        String password = null;
        String role = null;

        System.out.println("Enter your credentials to login.");

       while(username == null || password == null) {
           System.out.print("Username: ");
           username = userService.validateUsername();

           System.out.println("Password: ");
           password = userService.validatePassword();
       }

        role = userDAO.selectUserFromUsernameAndPassword(username, password);

        if (role == null) {
            app.getUserInfo().setRole("");
            System.out.println("Login unsuccessful.");
        } else {
            System.out.println("Login successful. Loading your dashboard.");
            Stack<Course> courses = courseDAO.selectAssociatedCourses(app.getUserInfo().getUserId(), role);

            try {
                app.setCourseList(courses);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String newRoute = role == "teacher" ? "/teacher" : "/student";
            router.route(newRoute);
        }
    }
}
