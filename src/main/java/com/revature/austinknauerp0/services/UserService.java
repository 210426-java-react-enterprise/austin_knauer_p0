package com.revature.austinknauerp0.services;

import com.revature.austinknauerp0.daos.UserDAO;
import com.revature.austinknauerp0.models.AppUser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {

    private UserDAO userDAO;
    private static Pattern hasSpace = Pattern.compile("\\s+");;
    private static Pattern hasDigit = Pattern.compile("\\d+");;
    private static Pattern hasCharacter = Pattern.compile("[!@#$%^&]+");;

    public UserService(UserDAO u) {
        userDAO = u;

    }

    // use custom exceptions here?

    public boolean validateUsername(String username) {
        if (!hasSpace.matcher(username).find() && 3 <= username.length() && username.length() <= 30) {
            return true;
        } else {
            return false;
        }
    }

    public void usernameAvailable(String username) {

    }

    public boolean validatePassword(String password) {
        if (!hasSpace.matcher(password).find() && hasDigit.matcher(password).find() && hasCharacter.matcher(password).find() && 6 <= password.length() && password.length() <= 30) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateName(String name) {
        if(!hasSpace.matcher(name).find() && !hasDigit.matcher(name).find() && !hasCharacter.matcher(name).find() && 1 <= name.length() && name.length() <= 30) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateEmail(String email) {
        // could use another regex to ensure email is in format xxx@xxx.xxx but that is beyond MVP
        if(!hasSpace.matcher(email).find() && 6 <= email.length() && email.length() <= 100) {
            return true;
        } else {
            return false;
        }
    }

    public void validateUserAndSave(AppUser user) {

    }
}
