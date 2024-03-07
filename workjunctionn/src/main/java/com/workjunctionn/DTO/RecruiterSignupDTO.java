package com.workjunctionn.DTO;

import jakarta.validation.constraints.*;


import lombok.Data;

@Data
public class RecruiterSignupDTO {

	// Recruiter signup properties

	private String username;

	private String recruiterName;

	@Email(message = "Incorrect format")
	private String email;

	private Long recruiterPhone;

	@Size(message = "lenght must be min size 8 and max size 16 characters")
	private String recruiterPassword;

	private String companyname;

}
