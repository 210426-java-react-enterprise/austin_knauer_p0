package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.AppState;

import java.io.BufferedReader;
import java.io.IOException;

public class Account extends Screen {

    public Account(UserDAO userDAO, UserService userService, BufferedReader inputRead) {
        super(userDAO, userService, inputRead);
        this.name = "Account";
        this.route = "/account";
    }

    @Override
    public void render() {

        AppState app = Driver.getApp();
        AppUser inf = app.getUserInfo();


        System.out.printf("Username: %s\nFirst Name: %s\nLast Name: %s\nEmail: %s\n", inf.getUsername(), inf.getFirstName(), inf.getLastName(), inf.getEmail());


        System.out.println("Options:");
        System.out.println("1) Change first name.");
        System.out.println("2) Change last name.");
        System.out.println("3) Change username.");
        System.out.println("4) Change password.");
        System.out.println("5) Change email.");
        System.out.println("6) Delete Account.");

        try {
            switch(inputRead.readLine()) {
                case "1":
                    System.out.print("New first name:");
                    String newFirstName = inputRead.readLine();
                    if(userService.validateName(newFirstName)) {
                        boolean success = userDAO.updateUser("first_name", newFirstName, app.getUserInfo().getUserId());
                        if (success)
                            System.out.println("First name updated successfully!");
                        else
                            System.out.println("Failed to update first name.");
                    } else {
                        System.out.println("Invalid entry.");
                    }
                    break;
                case "2":
                    System.out.print("New last name:");
                    String newLastName = inputRead.readLine();
                    if(userService.validateName(newLastName)) {
                        boolean success = userDAO.updateUser("last_name", newLastName, app.getUserInfo().getUserId());
                        if (success)
                            System.out.println("Last name updated successfully!");
                        else
                            System.out.println("Failed to update last name.");
                    } else {
                        System.out.println("Invalid entry.");
                    }
                    break;
                case "3":
                    System.out.print("New username:");
                    String newUsername = inputRead.readLine();
                    if(!userService.usernameAvailable(newUsername)) {
                        System.out.println("Username unavailable.");
                    } else if(userService.validateUsername(newUsername)) {
                        boolean success = userDAO.updateUser("username", newUsername, app.getUserInfo().getUserId());
                        if (success)
                            System.out.println("Username updated successfully!");
                        else
                            System.out.println("Failed to update username.");
                    } else {
                        System.out.println("Invalid entry.");
                    }
                    break;
                case "4":
                    System.out.print("Old password:");
                    String oldPassword = inputRead.readLine();
                    System.out.print("New password:");
                    String password = inputRead.readLine();
                    if(oldPassword != app.getUserInfo().getPassword()) {
                        System.out.println("Wrong password.");
                    } else if (userService.validatePassword(password)) {
                        boolean success = userDAO.updateUser("password", password, app.getUserInfo().getUserId());
                        if (success)
                            System.out.println("Password updated successfully!");
                        else
                            System.out.println("Failed to update password.");
                    } else {
                        System.out.println("Invalid entry.");
                    }
                    break;
                case "5":
                    System.out.print("New email:");
                    String newEmail = inputRead.readLine();
                    if(!userService.emailAvailable(newEmail)) {
                        System.out.println("Email already associated with an account.");
                    } else if (userService.validateEmail(newEmail)) {
                        boolean success = userDAO.updateUser("email", newEmail, app.getUserInfo().getUserId());
                        if (success)
                            System.out.println("Email updated successfully!");
                        else
                            System.out.println("Failed to update email.");
                    } else {
                        System.out.println("Invalid entry.");
                    }
                    break;
                case "6":
                    System.out.println("Are you sure you want to delete this account? All course associations will be deleted.");
                    System.out.print("Y/N");
                    String answer = inputRead.readLine();
                    if (answer == "Y") {
                        boolean success = userDAO.deleteUser(app.getUserInfo().getUserId());
                        if (success) {
                            System.out.println("Sorry to see you go. Your account has been deleted.");
                            app.setAppUserFirstName("");
                            app.setAppUserLastName("");
                            app.setAppUserEmail("");
                            app.setAppUserRole("");
                            app.setAppUserUsername("");
                            app.setAppUserPassword("");
                            app.setAppUserId(-1);
                        } else {
                            System.out.println("Delete unsuccessful.");
                        }

                        // does it need to be rerouted to welcome or login screen manually?
                    }
                    break;
                default:
                    System.out.println("Invalid Entry.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
