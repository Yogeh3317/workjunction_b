package com.workjunctionn.DTO;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RecruiterLoginDTO {

	@NotNull(message = "Email not matches")
	@Email(message = "Incorrect email format")
	private String email;
	
	
	@NotNull(message = "Password not matches")
	private String recruiterPassword;
}
