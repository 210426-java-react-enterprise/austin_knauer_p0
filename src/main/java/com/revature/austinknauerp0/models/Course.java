package com.revature.austinknauerp0.models;

public class Course {

    private String name;
    private String description;
    private int credits;
    private int teacherId;
    private int courseId;

    public Course() {
        this.name = "";
        this.description = "";
        this.credits = 0;
        this.teacherId = -1;
        this.courseId = -1;
    }

    public Course(String name, String description, int credits, int teacherId, int courseId) {
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
