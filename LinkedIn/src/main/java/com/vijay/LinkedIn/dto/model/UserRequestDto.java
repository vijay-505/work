package com.vijay.LinkedIn.dto.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
	
	@Email
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String name;
	private String about;

}
