package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.PeopleDAO;
import com.revature.austinknauerp0.models.Person;
import com.revature.austinknauerp0.services.UserService;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.ScreenRouter;
import com.revature.austinknauerp0.util.structures.Stack;


public class Directory extends Screen {

    public Directory(UserService userService, PeopleDAO peopleDAO, ScreenRouter router) {
        super(userService, peopleDAO, router);
        this.name = "Directory";
        this.route = "/directory";
    }

    @Override
    public void render() {

        AppState app = Driver.getApp();

        System.out.println("Options:");
        System.out.println("1) Students");
        System.out.println("2) Teachers");
        System.out.println("3) Back to Dashboard.");

        Integer selection = null;

        while (selection == null) {
            selection = userService.validateOptionSelection("1", "2", "3");
        }

        try {
            if (selection == 1) {
                Stack<Person> students = peopleDAO.selectAllStudents();
                System.out.println("Students:");
                for (int i = 0; i < students.size(); i++) {
                    Person student = students.pop();
                    System.out.printf("%s %s, email: %s", student.getFirstName(), student.getLastName(), student.getEmail());
                }
            } else if (selection == 2) {
                Stack<Person> teachers = peopleDAO.selectAllTeachers();
                System.out.println("Teachers:");
                for (int i = 0; i < teachers.size(); i++) {
                    Person teacher = teachers.pop();
                    System.out.printf("%s %s, email: %s", teacher.getFirstName(), teacher.getLastName(), teacher.getEmail());
                }
            } else if (selection == 3) {
                String nextRoute = app.getUserInfo().getRole() == "teacher" ? "/teacher" : "/student";
                router.route(nextRoute);
            } else {
                System.out.println("Invalid entry.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
