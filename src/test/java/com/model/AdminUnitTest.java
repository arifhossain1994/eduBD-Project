package com.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class AdminUnitTest {
    Admin obj= new Admin();

    @Test
    void adminTest()
    {
        String firstName= obj.getFirstName();
        assertNull(firstName);
        assertNull(obj.getEmail());
        assertNull(obj.getPassword());
    }

    @Test
    void nameTest() {
        obj.setFirstName("Arif");
        assertEquals("Arif", obj.getFirstName());
        obj.setLastName("1234");
        assertEquals("1234", obj.getLastName());
        assertNotEquals("5555", obj.getLastName());

    }

    @Test
    void statusTest()
    {
        obj.setStatus("suspended");
        assertEquals("suspended",obj.getStatus().toLowerCase());
        obj.setStatus("INACTIVE");
        assertEquals("inactive",obj.getStatus().toLowerCase());
        assertNotEquals("active",obj.getStatus().toLowerCase());
    }

}