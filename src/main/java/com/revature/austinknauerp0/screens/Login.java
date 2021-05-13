package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;

public class Login extends Screen {

    public Login(UserDAO userDAO, ScreenRouter router) {
        super(userDAO, router);
        this.name = "Login";
        this.route = "/login";
    }

    @Override
    public void render() {

        AppState app = Driver.getApp();

        String username = "";
        String password = "";
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
            String newRoute = role == "teacher" ? "/teacher" : "/student";
            router.route(newRoute);
        }
    }
}
