package com.revature.austinknauerp0.daos;

import com.revature.austinknauerp0.services.UserService;
import org.junit.After;
import org.junit.Before;

public class CourseDAOTest {

    private CourseDAO sut;

    @Before
    public void setUpTest() {
        sut = new CourseDAO();
    }

    @After
    public void tearDownTest() {
        sut = null;
    }
}
