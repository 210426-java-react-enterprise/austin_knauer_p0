package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;

public class Directory extends Screen {

    public Directory(UserDAO userDAO, BufferedReader inputRead) {
        super(userDAO, inputRead);
        this.name = "Directory";
        this.route = "/directory";
    }

    @Override
    public void render() {

        System.out.println("Options:");
        System.out.println("1) Students");
        System.out.println("2) Teachers");

        try {
            if (inputRead.readLine() == "1") {
                // show all students from database
            } else if (inputRead.readLine() == "2") {
                // show all teachers from database
            } else {
                System.out.println("Invalid Entry.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
