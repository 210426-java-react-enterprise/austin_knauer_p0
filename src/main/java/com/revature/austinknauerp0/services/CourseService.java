package com.revature.austinknauerp0.services;

import com.revature.austinknauerp0.daos.CourseDAO;

public class CourseService {

    private CourseDAO courseDAO;

    public CourseService() {

    }

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public boolean validateName(String name) {
        // could use another regex to ensure email is in format xxx@xxx.xxx but that is beyond MVP
        if(name != null &&  1 <= name.length() && name.length() <= 100) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateDescription(String desc) {
        // could use another regex to ensure email is in format xxx@xxx.xxx but that is beyond MVP
        if(desc != null && desc.length() <= 100) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateCredits(Integer credits) {
        // could use another regex to ensure email is in format xxx@xxx.xxx but that is beyond MVP
        if(credits != null && 0 < credits && credits < 8) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canEnroll(int credits, int userId) {
        if (credits + courseDAO.selectUserCredits(userId) < 22) {
            return true;
        } else {
            return false;
        }
    }

}
