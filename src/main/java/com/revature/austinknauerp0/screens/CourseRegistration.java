package com.revature.austinknauerp0.screens;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.PeopleDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.services.CourseService;
import com.revature.austinknauerp0.util.AppState;

import com.revature.austinknauerp0.util.ScreenRouter;
import com.revature.austinknauerp0.util.structures.Stack;

public class CourseRegistration extends Screen {

    public CourseRegistration(CourseDAO courseDAO, PeopleDAO peopleDAO, CourseService courseService, ScreenRouter router) {
        super(courseDAO, peopleDAO, courseService, router);
        this.name = "Course Registration";
        this.route = "/course-registration";
    }

    @Override
    public void render() {


        Integer toRegister = null;
        AppState app = Driver.getApp();
        Stack<Course> courses = courseDAO.selectUnregisteredCourses(app.getUserInfo().getUserId());



        if (courses.isEmpty()) {
            System.out.println("No available courses.");
            router.route("/student");
        } else {
            int[] courseIds = new int[courses.size()];
            int[] courseCredits = new int[courses.size()];
            try {
                for (int i = 0; i < courses.size(); i++) {
                    Course course = courses.pop();
                    courseIds[i] = course.getCourseId();
                    courseCredits[i] = course.getCredits();
                    System.out.printf("%s, %s, %s, %s, %s", course.getCourseId(), course.getName(), peopleDAO.selectTeacher(course.getTeacherId()), course.getDescription(), course.getCredits());

                }
            } catch (Exception e) {
                e.printStackTrace();
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

               toRegister = courseService.validateCourseIdEntry(courseIds);

               if (toRegister == null) {

                   router.route("/student");

               } else {

                   int index = 0;
                   for (int i = 0; i < courseIds.length; i++) {
                       if (courseIds[i] == toRegister) {
                           index = i;
                       }
                   }

                   int credits = courseCredits[index];
                   int studCredits = courseDAO.selectUserCredits(app.getUserInfo().getUserId());
                   if (!courseService.canEnroll(credits, app.getUserInfo().getUserId())) {
                       System.out.println("Not enough credit hours.");
                       router.route("/student");
                   } else {
                       if (courseDAO.insertEnrollment(toRegister, app.getUserInfo().getUserId(), credits + studCredits)) {
                           System.out.println("Enrollmemnt successful!");

                           Stack<Course> newCourses = courseDAO.selectAssociatedCourses(app.getUserInfo().getUserId(), "student");

                           try {
                               app.setCourseList(newCourses);
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }

                   }

               }

            } else if (selection == 2) {
                router.route("/student");
            }

        }


    }
}
