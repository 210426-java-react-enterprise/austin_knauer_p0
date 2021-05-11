package com.revature.austinknauerp0.util;

import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;
import com.revature.austinknauerp0.screens.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    // should bufferedreader be final?
    private BufferedReader inputRead;
    private final UserDAO userDAO;
    private AppUser appUser;
    private boolean isRunning;
    private ScreenRouter router;
    // still needs user service and screen router

    public AppState() {
        System.out.println("Application Starting...");
        isRunning = true;

        inputRead = new BufferedReader(new InputStreamReader(System.in));
        userDAO = new UserDAO();

        appUser = new AppUser();
        router = new ScreenRouter();

        router.add(new Welcome(userDAO, inputRead))
                .add(new Register(userDAO, inputRead))
                .add(new Login(userDAO, inputRead))
                .add(new Account(userDAO, inputRead))
                .add(new Student(userDAO, inputRead))
                .add(new Teacher(userDAO, inputRead))
                .add(new NewCourse(userDAO, inputRead))
                .add(new Directory(userDAO, inputRead))
                .add(new CourseRegistration(userDAO, inputRead));
        System.out.println("Application Ready!");
    }

    public void setIsRunning(boolean bool) {
        isRunning = bool;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public ScreenRouter getScreenRouter() {
        return router;
    }

    public AppUser getUserInfo() {
        return this.appUser;
    }

    public void setAppUserUsername(String username) {
        appUser.setUsername(username);
    }

    public void setAppUserFirstName(String firstName) {
        appUser.setFirstName(firstName);
    }

    public void setAppUserLastName(String lastName) {
        appUser.setLastName(lastName);
    }

    public void setAppUserPassword(String password) {
        appUser.setPassword(password);
    }

    public void setAppUserRole(String role) {
        appUser.setRole(role);
    }

}
