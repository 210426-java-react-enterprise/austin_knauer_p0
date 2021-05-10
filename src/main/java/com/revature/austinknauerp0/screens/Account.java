package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class Account extends Screen {

    public Account(UserDAO userDAO, BufferedReader inputRead) {
        super(userDAO, inputRead);
        this.name = "Account";
        this.route = "/account";
    }

    @Override
    public void render() {

        // access account info for user
        String username = null;
        // to be replaced by a custom data structure
        String[] accountInfo = new String[1];

        System.out.println(username);


        System.out.println("Options:");
        System.out.println("1) Change first name.");
        System.out.println("2) Change last name.");
        System.out.println("3) Change username.");
        System.out.println("4) Change password.");
        System.out.println("5) Delete Account.");

        try {
            switch(inputRead.readLine()) {
                case "1":
                    System.out.println("Current first name:");
                    // insert first name from account info
                    System.out.print("New first name:");
                    inputRead.readLine();
                    // update first name in database
                    break;
                case "2":
                    System.out.println("Current last name:");
                    // insert last name from account info
                    System.out.print("New last name:");
                    inputRead.readLine();
                    // update last name in database
                    break;
                case "3":
                    System.out.println("Current username:");
                    // insert first name from account info
                    System.out.print("New username:");
                    inputRead.readLine();
                    // Validate and update username in database
                    break;
                case "4":
                    System.out.println("Enter current password:");
                    inputRead.readLine();
                    // validate password
                    System.out.print("New password:");
                    inputRead.readLine();
                    System.out.println("Re-enter new password:");
                    // validate passwords and ensure they match, update password in database
                    break;
                case "5":
                    System.out.println("Are you sure you want to delete this account? All course associations will be deleted.");
                    System.out.print("Y/N");
                    inputRead.readLine();
                    // if y, delete user from database
                    break;
                default:
                    System.out.println("Invalid Entry.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
