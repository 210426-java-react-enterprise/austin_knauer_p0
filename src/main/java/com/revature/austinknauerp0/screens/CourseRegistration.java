package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.PeopleDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.services.CourseService;
import com.revature.austinknauerp0.util.AppState;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseRegistration extends Screen {

    public CourseRegistration(CourseDAO courseDAO, PeopleDAO peopleDAO, CourseService courseService, BufferedReader inputRead) {
        super(courseDAO, peopleDAO, courseService, inputRead);
        this.name = "Course Registration";
        this.route = "/course-registration";
    }

    @Override
    public void render() {

        // access list of all available courses minus currently registered ones
        // to be replaced by a custom data structure
        List<Course> courses = new ArrayList<>();
        String toRegister;
        AppState app = Driver.getApp();

        if (courses.isEmpty()) {
            System.out.println("No available courses.");
        } else {
            int[] courseIds = new int[courses.size()];
            for(Course course : courses) {
                // might need to format this better
                System.out.printf("%s, %s, %s, %s, %s", course.getCourseId(), course.getName(), peopleDAO.selectTeacher(course.getTeacherId()), course.getDescription(), course.getCredits());
            }

            System.out.println("Options: ");
            System.out.println("1) Register");
            System.out.println("2) Back to Dashboard");

            try {
                if (inputRead.readLine() == "1") {
                    System.out.print("Enter the id of the course you'd like to register for: ");
                    toRegister = inputRead.readLine();

                    boolean match = false;
                    int credits = 0;
                    for (int i = 0; i < courseIds.length; i++)
                        if (courseIds[i] == Integer.parseInt(toRegister)) {
                            match = true;
                            credits = courses.get(i).getCredits();
                    }

                    if (!match)
                        System.out.println("Invalid Course ID");
                    else if (!courseService.canEnroll(credits, app.getUserInfo().getUserId()))
                        System.out.println("Not enough credit hours to enroll.");
                    else
                        courseDAO.insertEnrollment(Integer.parseInt(toRegister), app.getUserInfo().getUserId());

                } else if (inputRead.readLine() == "2") {
                    return;
                } else {
                    System.out.println("Invalid Entry.");
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }


    }
}
