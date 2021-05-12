package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.daos.PeopleDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Directory extends Screen {

    public Directory(PeopleDAO peopleDAO, BufferedReader inputRead) {
        super(peopleDAO, inputRead);
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
                List<Person> students = new ArrayList<>();
                System.out.println("Students:");
                for (Person student : students)
                    System.out.printf("%s %s, email: %s", student.getFirstName(), student.getLastName(), student.getEmail());
            } else if (inputRead.readLine() == "2") {
                List<Person> teachers = new ArrayList<>();
                System.out.println("Teachers:");
                for (Person teacher : teachers)
                    System.out.printf("%s %s, email: %s", teacher.getFirstName(), teacher.getLastName(), teacher.getEmail());
            } else {
                System.out.println("Invalid Entry.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
