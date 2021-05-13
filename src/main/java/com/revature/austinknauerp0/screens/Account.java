package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;

public class Account extends Screen {

    public Account(UserDAO userDAO, UserService userService, ScreenRouter router) {
        super(userDAO, userService, router);
        this.name = "Account";
        this.route = "/account";
    }

    @Override
    public void render() {

        AppState app = Driver.getApp();
        AppUser inf = app.getUserInfo();
        String role = app.getUserInfo().getRole() == "teacher" ? "/teacher" : "/student";


        System.out.printf("Username: %s\nFirst Name: %s\nLast Name: %s\nEmail: %s\n", inf.getUsername(), inf.getFirstName(), inf.getLastName(), inf.getEmail());


        System.out.println("Options:");
        System.out.println("1) Change first name.");
        System.out.println("2) Change last name.");
        System.out.println("3) Change username.");
        System.out.println("4) Change password.");
        System.out.println("5) Change email.");
        System.out.println("6) Delete Account.");

        Integer selection = null;

        while(selection == null) {
            selection = userService.validateOptionSelection("1", "2", "3", "4", "5", "6");
        }

        switch(selection) {
            case 1:

                String newFirstName = null;

                while (newFirstName == null) {
                    System.out.print("New first name: ");
                    newFirstName = userService.validateName();
                }

                boolean success = userDAO.updateUser("first_name", newFirstName, app.getUserInfo().getUserId());
                if (success) {
                    System.out.println("First name updated successfully!");
                    app.setAppUserFirstName(newFirstName);
                    router.route(role);
                }
                else
                    System.out.println("Failed to update first name.");

                // not ideal to change the user's first name from the entry here rather than from the database
                break;

            case 2:

                String newLastName = null;

                while (newLastName == null) {
                    System.out.print("New last name: ");
                    newLastName = userService.validateName();
                }

                boolean successLN = userDAO.updateUser("last_name", newLastName, app.getUserInfo().getUserId());
                if (successLN) {
                    System.out.println("Last name updated successfully!");
                    app.setAppUserFirstName(newLastName);
                }
                else
                    System.out.println("Failed to update last name.");

                router.route(role);

                break;

            case 3:

                String newUsername = null;

                while(newUsername == null) {
                    System.out.print("New username: ");
                    newUsername = userService.validateUsername();
                    if (newUsername != null) {
                        newUsername = !userService.usernameAvailable(newUsername) ? null : newUsername;
                    }
                }
                boolean successUN = userDAO.updateUser("username", newUsername, app.getUserInfo().getUserId());
                if (successUN) {
                    System.out.println("Username updated successfully!");
                    app.setAppUserUsername(newUsername);
                    router.route(role);
                } else
                        System.out.println("Failed to update username.");

                break;

            case 4:

                String newPassword = null;

                while (newPassword == null) {
                    System.out.print("New password: ");
                    newPassword = userService.validatePassword();
                }

                boolean successPW = userDAO.updateUser("password", newPassword, app.getUserInfo().getUserId());
                if (successPW) {
                    System.out.println("Password updated successfully!");
                    app.setAppUserPassword(newPassword);
                    router.route(role);
                } else
                    System.out.println("Failed to update password.");

            break;

            case 5:
                String newEmail = null;

                while(newEmail == null) {
                    System.out.print("New email: ");
                    newEmail = userService.validateEmail();
                    if (newEmail != null) {
                        newEmail = !userService.emailAvailable(newEmail) ? null : newEmail;
                    }
                }

                boolean successE = userDAO.updateUser("email", newEmail, app.getUserInfo().getUserId());
                if (successE) {
                    System.out.println("Email updated successfully!");
                    app.setAppUserEmail(newEmail);
                    router.route(role);
                } else
                    System.out.println("Failed to update email.");

                break;

            case 6:
                Integer confirmDelete = null;

                while (confirmDelete == null) {
                    System.out.println("Are you sure you want to delete this account? All course associations will be deleted. Enter 1 to COnFIRM DELETE and 2 to CANCEL.");
                    confirmDelete = userService.validateOptionSelection("1", "2");
                }


                if (confirmDelete == 1) {
                    boolean successDel = userDAO.deleteUser(app.getUserInfo().getUserId());
                    if (successDel) {
                        System.out.println("Sorry to see you go. Your account has been deleted.");
                        app.setAppUserFirstName("");
                        app.setAppUserLastName("");
                        app.setAppUserEmail("");
                        app.setAppUserRole("");
                        app.setAppUserUsername("");
                        app.setAppUserPassword("");
                        app.setAppUserId(-1);
                        app.setCurrentCourse(null);
                        app.emptyCourseList();
                    } else {
                        System.out.println("Delete unsuccessful.");
                    }

                    router.route("/welcome");
                    // does it need to be rerouted to welcome or login screen manually?
                }
                break;
            default:
                System.out.println("Invalid Entry.");

        }
    }

}
