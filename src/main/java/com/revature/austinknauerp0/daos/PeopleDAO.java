package com.revature.austinknauerp0.daos;

import com.revature.austinknauerp0.models.Person;
import com.revature.austinknauerp0.util.JDBConnectionMaker;
import com.revature.austinknauerp0.util.structures.Stack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PeopleDAO {

    public boolean insertStudent (int userId) {

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "insert into students (student_id, creditHours, user_id) values (default, 0, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            int insertedRows = pstmt.executeUpdate();

            if (insertedRows != 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertTeacher (int userId) {

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "insert into teachers (student_id, user_id) values (default, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            int insertedRows = pstmt.executeUpdate();

            if (insertedRows != 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Stack<Person> selectEnrolledStudents (int courseId) {

        Stack<Person> students = new Stack<>();

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select username, first_name, last_name from users inner join students on users.user_id = students.user_id inner join enrollments on students.student_id = enrollments.student_id and enrollments.course_id = 14";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Person currentStudent = new Person(rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"));
                students.add(currentStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public Stack<Person> selectAllStudents () {

        Stack<Person> students = new Stack<>();

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select first_name, last_name, email, username from users where role = 'student'";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Person currentStudent = new Person(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("username"));
                students.add(currentStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public Stack<Person> selectAllTeachers() {

        Stack<Person> teachers = new Stack<>();

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select first_name, last_name, email, username from users where role = 'teacher'";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Person currentTeacher = new Person(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getString("username"));
                teachers.add(currentTeacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public Person selectTeacher(int teacherId) {

        Person teacher = new Person();

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select first_name, last_name from users where user_id = (select user_id from teachers where teacher_id = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, teacherId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;
    }



}
