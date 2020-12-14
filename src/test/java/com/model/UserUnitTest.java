package com.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;


public class UserUnitTest {

    User obj= new User();

    @Test
    public void testUser()
    {
        String name= obj.getName();
        assertNull(name);
        assertNull(obj.getEmail());
        assertNull(obj.getPassword());
    }

    @Test
    public void testName() {
        obj.setName("Arif Hossain");
        assertEquals("Arif Hossain", obj.getName());
        obj.setName("1234");
        assertEquals("1234", obj.getName());
        assertNotEquals("5555", obj.getName());

    }

    @Test
    public void testStatus()
    {
        obj.setStatus("suspended");
        assertEquals("suspended",obj.getStatus().toLowerCase());
        obj.setStatus("INACTIVE");
        assertEquals("inactive",obj.getStatus().toLowerCase());
        assertNotEquals("active",obj.getStatus().toLowerCase());
    }

}
