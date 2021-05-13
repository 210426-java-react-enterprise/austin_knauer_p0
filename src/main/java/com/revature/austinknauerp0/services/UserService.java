package com.revature.austinknauerp0.services;

import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private UserDAO userDAO;
    private BufferedReader inputRead;
    private static Pattern hasSpace = Pattern.compile("\\s+");;
    private static Pattern hasDigit = Pattern.compile("\\d+");;
    private static Pattern hasCharacter = Pattern.compile("[!@#$%^]+");

    public UserService() {
    }

    public UserService(UserDAO userDAO, BufferedReader inputRead) {
        this.userDAO = userDAO;
        this.inputRead = inputRead;
    }


    // use custom exceptions here?

    public String validateUsername() {

        String un = "";

        try {
            un = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (un != null && !hasSpace.matcher(un).find() && 3 <= un.length() && un.length() <= 30) {
            return un;
        } else {
            System.out.println("Invalid entry. Usernames must not contain any spaces and must be between 3 and 30 characters.");
            return null;
        }
    }

    public boolean usernameAvailable(String username) {
        if (!userDAO.selectUserFromX("username", username)) {
            System.out.println("Username unavailable. Please try another username.");
            return false;
        }

        return true;
    }

    public boolean emailAvailable(String email) {
        if (!userDAO.selectUserFromX("email", email)) {
            System.out.println("Email already associated with an account. Please try another email.");
            return false;
        }

        return true;


    }

    public String validatePassword() {

        String pw = "";

        try {
            pw = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (pw != null && !hasSpace.matcher(pw).find() && hasDigit.matcher(pw).find() && hasCharacter.matcher(pw).find() && 6 <= pw.length() && pw.length() <= 30) {
            return pw;
        } else {
            System.out.println("Invalid entry. Passwords cannot contain white space, must be between 6 and 30 characters, and must contain one number and one special character (!@#$%^).");
            return null;
        }
    }

    public String validateName() {

        String name = "";

        try {
            name = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(name != null && !hasSpace.matcher(name).find() && !hasDigit.matcher(name).find() && !hasCharacter.matcher(name).find() && 1 <= name.length() && name.length() <= 30) {
            return name;
        } else {
            System.out.println("Invalid entry. Names can only contain letters A-Z and must be between 1 and 30 characters.");
            return null;
        }
    }

    public String validateEmail() {

        String email = "";

        try {
            email = inputRead.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // could use another regex to ensure email is in format xxx@xxx.xxx but that is beyond MVP
        if(email != null && !hasSpace.matcher(email).find() && 6 <= email.length() && email.length() <= 100) {
            return email;
        } else {
            System.out.println("Invalid entry. Emails must contain no white space and be between 6 and 100 characters.");
            return null;
        }
    }

    public boolean validateUserAndSave(String firstName, String lastName, String username, String password, String email, String role) {
       // change name if nothing actually gets validated here
        return userDAO.insertNewUser(firstName, lastName,username, password, email, role);

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
            if (entry.equals(option))
                return Integer.parseInt(option);

        }
        return null;

    }
}
