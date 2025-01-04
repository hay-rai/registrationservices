package com.hay.user.registrationservices.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String userName;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String mobileNumber;
	private String password;
	private boolean isDeleted;
}
