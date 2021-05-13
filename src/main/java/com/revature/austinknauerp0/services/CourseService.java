package com.revature.austinknauerp0.services;

import com.revature.austinknauerp0.daos.CourseDAO;

import java.io.BufferedReader;

public class CourseService {

    private CourseDAO courseDAO;
    private BufferedReader inputRead;

    public CourseService() {

    }

    public CourseService(CourseDAO courseDAO, BufferedReader inputRead) {
        this.courseDAO = courseDAO;
        this.inputRead = inputRead;
    }

    public String validateName() {

        String name = null;

        try {
            name = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(name != null &&  1 <= name.length() && name.length() <= 100) {
            return name;
        } else {
            System.out.println("Invalid entry. Course name must be between 1 and 100 characters.");
            return null;
        }
    }

    public String validateDescription() {

        String desc = null;

        try {
            desc = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(desc != null && desc.length() <= 1000) {
            return desc;
        } else {
            System.out.println("Invalid entry. Descriptions must be less than 1000 characters.");
            return null;
        }
    }

    public Integer validateCredits() {

        String cred = null;

        try {
            cred = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer finalCredits = null;

        if (cred != null && !cred.isEmpty()) {
          finalCredits = Integer.parseInt(cred);
        }


        if(0 < finalCredits && finalCredits < 8) {
            return finalCredits;
        } else {
            System.out.println("Invalid entry. Courses can have a minimum of 1 and maximum of 8 credit hours.");
            return null;
        }
    }

    public boolean canEnroll(int credits, int userId) {
        if (credits + courseDAO.selectUserCredits(userId) < 22) {
            return true;
        } else {
            return false;
        }
    }

    public Integer validateCourseIdEntry(int[] ids) {
        String entry = null;

        try {
            entry = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (entry.isEmpty() || entry == null) {
            System.out.println("Invalid entry. Please try again.");
            return null;
        }

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == Integer.parseInt(entry)) {
                return ids[i];
            }
        }

        System.out.println("Course ID not found. Please try again.");
        return null;
    }

    public Integer validateOptionSelection(String... options) {

        String entry = "";

        try {
            entry = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (entry.isEmpty() || entry == null) {
            System.out.println("Invalid entry. Please try again.");
            return null;
        }

        for (String option : options) {
            if (entry == option)
                return Integer.parseInt(option);
        }

        return null;

    }

}
