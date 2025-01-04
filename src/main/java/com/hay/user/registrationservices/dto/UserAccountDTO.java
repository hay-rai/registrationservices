package com.hay.user.registrationservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;

	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Please provide a valid email address")
	private String emailAddress;
	@NotEmpty(message = "Mobile number cannot be empty")
	@Pattern(regexp = "^\\+([1-9]{1}[0-9]{1,3})\\s?([0-9]{6,14})$", message = "Please provide a valid mobile number")
	private String mobileNumber;
	private String password;
}
