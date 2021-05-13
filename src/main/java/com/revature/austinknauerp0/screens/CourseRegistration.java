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
import com.revature.austinknauerp0.util.structures.Stack;

public class CourseRegistration extends Screen {

    public CourseRegistration(CourseDAO courseDAO, PeopleDAO peopleDAO, CourseService courseService) {
        super(courseDAO, peopleDAO, courseService);
        this.name = "Course Registration";
        this.route = "/course-registration";
    }

    @Override
    public void render() {

        // access list of all available courses minus currently registered ones
        Stack<Course> courses = new Stack<>();
        Integer toRegister = null;
        AppState app = Driver.getApp();

        if (courses.isEmpty()) {
            System.out.println("No available courses.");
        } else {
            int[] courseIds = new int[courses.size()];
            int[] courseCredits = new int[courses.size()];
            for (int i = 0; i < courses.size(); i++) {
                try {
                    Course course = courses.pop();
                    courseIds[i] = course.getCourseId();
                    courseCredits[i] = course.getCredits();
                    System.out.printf("%s, %s, %s, %s, %s", course.getCourseId(), course.getName(), peopleDAO.selectTeacher(course.getTeacherId()), course.getDescription(), course.getCredits());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Options: ");
            System.out.println("1) Register");
            System.out.println("2) Back to Dashboard");

            Integer selection = null;

            while (selection == null) {
                selection = userService.validateOptionSelection("1", "2");
            }

            if (selection == 1) {
                System.out.print("Enter the id of the course you'd like to register for: ");
                while (toRegister == null) {
                    toRegister = courseService.validateCourseIdEntry(courseIds);
                }

                /* handle finding the associated course and seeing if user can register
                else if (!courseService.canEnroll(credits, app.getUserInfo().getUserId()))
                    System.out.println("Not enough credit hours to enroll.");
                else
                    courseDAO.insertEnrollment(Integer.parseInt(toRegister), app.getUserInfo().getUserId());

                 */

            } else if (selection == 2) {
                router.route("/student");
            }

        }


    }
}
