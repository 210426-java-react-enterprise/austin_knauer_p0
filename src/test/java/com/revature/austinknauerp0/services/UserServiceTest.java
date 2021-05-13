package com.revature.austinknauerp0.services;

import com.revature.austinknauerp0.daos.UserDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;

public class UserServiceTest {

    private UserService sut;
    private BufferedReader mockInputRead;
    private UserDAO mockUserDAO;

    @Before
    public void setUpTest() {

        mockInputRead = mock(BufferedReader.class);
        mockUserDAO = mock(UserDAO.class);
        sut = new UserService(mockUserDAO, mockInputRead);

    }

    @After
    public void tearDownTest() {

        sut = null;
        mockInputRead = null;
        mockUserDAO = null;

    }

    @Test
    public void test_validateUsernameWithValidUsername() throws IOException {

        // Arrange
        String input = "abcdef";
        String expectedOutput = "abcdef";
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateUsername();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);


    }

    @Test
    public void test_validateUsernameWithInvalidUsername() throws IOException {

        // Arrange
        String input = "abbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
        String expectedOutput = null;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateUsername();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);


    }

    @Test
    public void test_usernameAvailableWithAvailableUsername() {

        // Arrange
        String input = "abcdef";
        boolean expectedResult = true;
        when(mockUserDAO.selectUserFromX(any(), any())).thenReturn(true);

        // Act
        boolean actual = sut.usernameAvailable(input);

        // Assert
        verify(mockUserDAO, times(1)).selectUserFromX(anyString(), any());
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void test_usernameAvailableWithUnavailableUsername() {

        // Arrange
        String input = "abcdef";
        boolean expectedResult = false;
        when(mockUserDAO.selectUserFromX(any(), any())).thenReturn(false);

        // Act
        boolean actual = sut.usernameAvailable(input);

        // Assert
        verify(mockUserDAO, times(1)).selectUserFromX(anyString(), any());
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void test_emailAvailableWithAvailableEmail() {

        // Arrange
        String input = "abcdef@ajls";
        boolean expectedResult = true;
        when(mockUserDAO.selectUserFromX(any(), any())).thenReturn(true);

        // Act
        boolean actual = sut.emailAvailable(input);

        // Assert
        verify(mockUserDAO, times(1)).selectUserFromX(anyString(), any());
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void test_emailAvailableWithUnavailableEmail() {

        // Arrange
        String input = "abcdef@ajls";
        boolean expectedResult = false;
        when(mockUserDAO.selectUserFromX(any(), any())).thenReturn(false);

        // Act
        boolean actual = sut.emailAvailable(input);

        // Assert
        verify(mockUserDAO, times(1)).selectUserFromX(anyString(), any());
        Assert.assertEquals(expectedResult, actual);
    }

    @Test
    public void test_validatePasswordWithValidPassword() throws IOException {

        // Arrange
        String input = "abcdef1$";
        String expectedOutput = "abcdef1$";
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validatePassword();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validatePasswordWithInvalidPassword() throws IOException {

        // Arrange
        String input = "abcdef1";
        String expectedOutput = null;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validatePassword();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateNameWithValidName() throws IOException {

        // Arrange
        String input = "Austin";
        String expectedOutput = "Austin";
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateName();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateNameWithInvalidName() throws IOException {

        // Arrange
        String input = "Austi n";
        String expectedOutput = null;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateName();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateEmailWithValidEmail() throws IOException {

        // Arrange
        String input = "abcdef@ajald";
        String expectedOutput = "abcdef@ajald";
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateEmail();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateEmailWithInvalidEmail() throws IOException {

        // Arrange
        String input = "ab";
        String expectedOutput = null;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateEmail();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateUserAndSave() {

        // Arrange
        boolean expectedResult = true;
        when(mockUserDAO.insertNewUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(true);

        // Act
        boolean output = sut.validateUserAndSave("abcdf", "adddd", "ajsalaj", "ajjdja", "ajjdaj@ajj", "student");

        // Assert
        verify(mockUserDAO, times(1)).insertNewUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
        Assert.assertEquals(expectedResult, output);

    }

    @Test
    public void test_validateOptionSelectionWithValidOptionSelection() throws IOException {

        // Arrange
        String input = "2";
        Integer expectedResult = 2;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        Integer output = sut.validateOptionSelection("1", "2");

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedResult, output);


    }

    @Test
    public void test_validateOptionSelectionWithInvalidOptionSelection() throws IOException {

        // Arrange
        String input = "4";
        Integer expectedResult = null;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        Integer output = sut.validateOptionSelection("1", "2");

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedResult, output);


    }
}
