package com.model;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class UserUnitTest{
	User user=new User();
	
	@Test
	public void testUserFirstName() {
	String s=user.getFirst_name();
	assertNull(s);
	}

	@Test
	public void testUserPassword(){
		String s=user.getPassword();
		assertNull(s);
	}
}