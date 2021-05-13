package com.revature.austinknauerp0.daos;

import com.revature.austinknauerp0.services.UserService;
import org.junit.After;
import org.junit.Before;

public class PeopleDAOTest {

    private PeopleDAO sut;

    @Before
    public void setUpTest() {
        sut = new PeopleDAO();
    }

    @After
    public void tearDownTest() {
        sut = null;
    }
}
