package com.revature.austinknauerp0.daos;

import com.revature.austinknauerp0.Driver;
import com.revature.austinknauerp0.models.AppUser;
import com.revature.austinknauerp0.util.AppState;
import com.revature.austinknauerp0.util.JDBConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean selectUserFromX(String field, String value) {

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select * from users where ? = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, field);
            pstmt.setString(2, value);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public String selectUserFromUsernameAndPassword(String username, String password) {

        AppState app = Driver.getApp();
        String role = null;

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                // bad practice to do this in the user dao?
                app.setAppUserId(rs.getInt("user_id"));
                app.setAppUserUsername(rs.getString("username"));
                app.setAppUserPassword(rs.getString("password"));
                app.setAppUserEmail(rs.getString("email"));
                app.setAppUserFirstName(rs.getString("first_name"));
                app.setAppUserLastName(rs.getString("last_name"));
                app.setAppUserRole(rs.getString("role"));
                role = rs.getString("role");
                return role;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    public boolean insertNewUser(String username, String password, String firstName, String lastName, String email, String role) {

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "insert into users (user_id, username, password, first_name, last_name, email, role) values (default, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "user_id" });
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5, email);
            pstmt.setString(6, role);
            int insertedRows = pstmt.executeUpdate();

            if (insertedRows != 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean deleteUser(int userId) {

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "delete from users where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            int deletedRecords = pstmt.executeUpdate();

            if (deletedRecords != 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean updateUser(String updateCol, String updateVal, int userId) {

        try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {

            String sql = "update users set ? = ? where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, updateCol);
            pstmt.setString(2, updateVal);
            pstmt.setInt(3, userId);

            int updatedRecords = pstmt.executeUpdate();

            if (updatedRecords != 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
}
