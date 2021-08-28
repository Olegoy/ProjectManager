package com.example.yashkin.utils;

import org.junit.Assert;
import org.junit.Test;

public class UsersTest {

    String firstName = "Ivan ";
    String lastName = "Ivanov";

    @Test
    public void getFullName() {
        Assert.assertEquals("IvanIvanov", Users.getFullName(firstName, lastName));
    }

    @Test
    public void testGetFullName() {
        Assert.assertEquals("Ivan", Users.getFullName(firstName));
    }

}