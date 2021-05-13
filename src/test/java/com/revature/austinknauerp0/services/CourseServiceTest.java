package com.revature.austinknauerp0.services;

import com.revature.austinknauerp0.daos.CourseDAO;
import com.revature.austinknauerp0.daos.UserDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CourseServiceTest {

    private CourseService sut;
    private BufferedReader mockInputRead;
    private CourseDAO mockCourseDAO;

    @Before
    public void setUpTest() {

        mockInputRead = mock(BufferedReader.class);
        mockCourseDAO = mock(CourseDAO.class);
        sut = new CourseService(mockCourseDAO, mockInputRead);

    }

    @After
    public void tearDownTest() {

        sut = null;
        mockInputRead = null;
        mockCourseDAO = null;

    }

    @Test
    public void test_validateNameWithValidName() throws IOException {
        // Arrange
        String input = "Class";
        String expectedOutput = "Class";
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
        String input = "";
        String expectedOutput = null;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateName();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateDescriptionWithValidDescription() throws IOException {
        // Arrange
        String input = "This is a valid class description.";
        String expectedOutput = "This is a valid class description.";
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateDescription();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateDescriptionWithInalidDescription() throws IOException {
        // Arrange
        String input = null;
        String expectedOutput = null;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        String result = sut.validateDescription();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateCreditsWithValidCredits() throws IOException {
        // Arrange
        String input = "3";
        Integer expectedOutput = 3;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        Integer result = sut.validateCredits();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_validateCreditsWithInvalidCredits() throws IOException {
        // Arrange
        String input = "22";
        Integer expectedOutput = null;
        when(mockInputRead.readLine()).thenReturn(input);

        // Act
        Integer result = sut.validateCredits();

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expectedOutput, result);

    }

    @Test
    public void test_canEnrollWithEnoughCredits() {

        // Arrange
        int inputCredits = 3;
        when(mockCourseDAO.selectUserCredits(anyInt())).thenReturn(3);
        boolean expected = true;

        // Act
        boolean result = sut.canEnroll(inputCredits, 3);

        // Assert
        verify(mockCourseDAO, times(1)).selectUserCredits(anyInt());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test_canEnrollWithoutEnoughCredits() {

        // Arrange
        int inputCredits = 3;
        when(mockCourseDAO.selectUserCredits(anyInt())).thenReturn(33);
        boolean expected = false;

        // Act
        boolean result = sut.canEnroll(inputCredits, 3);

        // Assert
        verify(mockCourseDAO, times(1)).selectUserCredits(anyInt());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test_validateCourseIdEntryWithValidEntry() throws IOException {

        // Arrange
        int[] input = new int[] {1, 2, 3};
        when(mockInputRead.readLine()).thenReturn("2");
        Integer expected = 2;

        // Act
        Integer result = sut.validateCourseIdEntry(input);

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void test_validateCourseIdEntryWithInvalidEntry() throws IOException {

        // Arrange
        int[] input = new int[] {1, 2, 3};
        when(mockInputRead.readLine()).thenReturn("23");
        Integer expected = null;

        // Act
        Integer result = sut.validateCourseIdEntry(input);

        // Assert
        verify(mockInputRead, times(1)).readLine();
        Assert.assertEquals(expected, result);
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


