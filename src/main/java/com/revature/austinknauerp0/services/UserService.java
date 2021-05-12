package com.revature.austinknauerp0.services;

import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private UserDAO userDAO;
    private static Pattern hasSpace = Pattern.compile("\\s+");;
    private static Pattern hasDigit = Pattern.compile("\\d+");;
    private static Pattern hasCharacter = Pattern.compile("[!@#$%^]+");;

    public UserService(UserDAO u) {
        userDAO = u;

    }

    // use custom exceptions here?

    public boolean validateUsername(String username) {
        if (username != null && !hasSpace.matcher(username).find() && 3 <= username.length() && username.length() <= 30) {
            return true;
        } else {
            return false;
        }
    }

    public boolean usernameAvailable(String username) {
        return userDAO.selectUserFromX("username", username);
    }

    public boolean emailAvailable(String email) {
        return userDAO.selectUserFromX("email", email);
    }

    public boolean validatePassword(String password) {
        if (password != null && !hasSpace.matcher(password).find() && hasDigit.matcher(password).find() && hasCharacter.matcher(password).find() && 6 <= password.length() && password.length() <= 30) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateName(String name) {
        if(name != null && !hasSpace.matcher(name).find() && !hasDigit.matcher(name).find() && !hasCharacter.matcher(name).find() && 1 <= name.length() && name.length() <= 30) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateEmail(String email) {
        // could use another regex to ensure email is in format xxx@xxx.xxx but that is beyond MVP
        if(email != null && !hasSpace.matcher(email).find() && 6 <= email.length() && email.length() <= 100) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateUserAndSave(String firstName, String lastName, String username, String password, String email, String role) {
       // change name if nothing actually gets validated here
        return userDAO.insertNewUser(firstName, lastName,username, password, email, role);

    }
}
