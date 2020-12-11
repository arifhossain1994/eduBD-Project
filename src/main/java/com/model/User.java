package com.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User{
	Long id;
	private String first_name, last_name;
	private String email, password;
	private LocalDateTime created_time;

	
	
	
	
}