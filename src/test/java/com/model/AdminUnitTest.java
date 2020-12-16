package com.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;


public class AdminUnitTest {

    Admin obj= new Admin();

    @Test
    public void testAdmin()
    {
        String firstName= obj.getFirst_name();
        assertNull(firstName);
        assertNull(obj.getEmail());
        assertNull(obj.getPassword());
    }

    @Test
    public void testName() {
        obj.setFirst_name("Arif");
        assertEquals("Arif", obj.getFirst_name());
        obj.setLast_name("1234");
        assertEquals("1234", obj.getLast_name());
        assertNotEquals("5555", obj.getLast_name());

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
