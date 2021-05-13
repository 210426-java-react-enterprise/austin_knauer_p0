package com.revature.austinknauerp0.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class JDBConnectionMakerTest {

        @Test
        public void test_getConnection() {
            String error = null;
            try (Connection conn = JDBConnectionMaker.getInstance().getConnection()) {
                Assert.assertNotNull(conn);
            } catch (Exception e) {
                error = "None";
            }

            Assert.assertNull(error);
        }

}
