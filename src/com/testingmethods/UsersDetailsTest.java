package com.testingmethods;

import com.users.UsersData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UsersDetailsTest {
    UsersData usersData = null;
    @Before
    public void setUp(){
        usersData = new UsersData();
    }
    @After
    public void tearDown(){
        usersData = null;
    }
    @Test
    public void userSingInTest1(){
       assertEquals(0,usersData.userSignIn("Kushagra Tripathi",887654323L,"kush12345@gmail.com","ku123"));
    }
    @Test
    public void userLoginTest1(){
        assertEquals(1,usersData.userLoginPage("rajeev123@gmail.com","raj123"));
    }
    @Test
    public void validateUser1(){
        assertEquals(1,usersData.validateUser("rajeev123@gmail.com","raj123"));
    }
    @Test
    public void userSingInTest2(){
        assertNotEquals(1,usersData.userSignIn("Kushagra Tripathi",887654323L,"kush12345@gmail.com","ku123"));
    }
    @Test
    public void userLoginTest2(){
        assertNotEquals(0,usersData.userLoginPage("rajeev123@gmail.com","raj123"));
    }
    @Test
    public void validateUser2(){
        assertNotEquals(0,usersData.validateUser("rajeev123@gmail.com","raj123"));
    }
}
