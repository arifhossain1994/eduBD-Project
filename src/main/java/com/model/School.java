package com.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class School extends User 
{
	
	private String street_address;
	private int zip_code;
	private String city;

	
}