package com.revature.austinknauerp0.daos;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.models.AppUser;
import com.revature.austinknauerp0.models.Course;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.JDBConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public List<Course> selectAssociatedCourses(int userId, String role) {

        List<Course> courses = new ArrayList<Course>();
        // needs to be replaced with custom data structure

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = role == "teacher" ? "select * from courses where teacher_id = (select teacher_id from teachers where user_id = ?)"
            : "select courses.course_id, name, description, teacher_id, credit_hours from courses inner join enrollments on courses.course_id = enrollments.course_id inner join students on enrollments.student_id = students.student_id and students.user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            int index = 0;
            while(rs.next()) {
                courses.add(new Course());
                Course currentCourse = courses.get(index);
                currentCourse.setName(rs.getString("name"));
                currentCourse.setTeacherId(rs.getInt("teacher_id"));
                currentCourse.setCourseId(rs.getInt("course_id"));
                index++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Course> selectUnregisteredCourses(int userId) {

        List<Course> courses = new ArrayList<Course>();
        // needs to be replaced with custom data structure

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select * from courses except select courses.course_id, name, description, teacher_id, credit_hours from courses inner join enrollments on courses.course_id = enrollments.course_id inner join students on enrollments.student_id = students.student_id and students.user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            int index = 0;
            while(rs.next()) {
                courses.add(new Course());
                Course currentCourse = courses.get(index);
                currentCourse.setName(rs.getString("name"));
                currentCourse.setTeacherId(rs.getInt("teacher_id"));
                currentCourse.setCourseId(rs.getInt("course_id"));
                index++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    public Course selectCourseDetails(Course course) {

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select * from courses where course_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, course.getCourseId());

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                course.setName(rs.getString("name"));
                course.setCredits(rs.getInt("credit_hours"));
                course.setTeacherId(rs.getInt("teacher_id"));
                course.setDescription(rs.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }

    public boolean deleteCourse(int courseId) {

        try(Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "delete from courses where course_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseId);

            int deletedRecords = pstmt.executeUpdate();
            if (deletedRecords != 0) {
                return true;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteEnrollment(int courseId, int userId) {

        try(Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "delete from enrollments where course_id = ? and student_id = (select student_id from students where user_id = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, userId);

            int deletedRecords = pstmt.executeUpdate();
            if (deletedRecords != 0) {
                return true;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insertEnrollment(int courseId, int userId) {
        try(Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "insert into enrollments (course_id, student_id) values (?, (select student_id from students where user_id = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, userId);

            int insertedRecords = pstmt.executeUpdate();
            if (insertedRecords != 0) {
                return true;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean insertCourse(String name, String description, int credits, int userId) {
            try(Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

                String sql = "insert into courses (course_id, name, description, credit_hours, teacher_id) values (default, ?, ?, ?, (select teacher_id from teachers where user_id = ?))";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, description);
                pstmt.setInt(3,  credits);
                pstmt.setInt(4, userId);

                int insertedRecords = pstmt.executeUpdate();
                if (insertedRecords != 0) {
                    return true;
                }

            } catch(Exception e) {
                e.printStackTrace();
            }

            return false;
        }

    public int selectUserCredits(int userId) {

        int credits = 0;

        try(Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select enrolled_credits from students where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                credits = rs.getInt("enrolled_credits");
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return credits;
    }
}

