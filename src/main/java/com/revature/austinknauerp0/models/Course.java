package com.revature.austinknauerp0.models;

public class Course {

    private String name;
    private String description;
    private String credits;
    private String teacher;

    public Course(String name, String description, String credits, String teacher) {
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.teacher = teacher;
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

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
