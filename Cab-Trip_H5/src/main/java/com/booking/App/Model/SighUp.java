package com.booking.App.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

public class SighUp {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull
    @Pattern(regexp="[a-zA-Z0-9]{4,15}", message = "Username must be between 4 to 15 characters & should only contain lowercase characters.")
    private String userName;

    @NotNull
    @Pattern(regexp="[a-zA-Z0-9]{5,15}",message="Password should be between 5 to 15 characters and alphanumeric with both Uppercase and lowercase characters.")
    private String password;

    @NotNull
    @Pattern(regexp="[0-9]{10}", message = "Mobile number should have 10 digits")
    private String mobileNo;

    @Email
    @NotNull
    private String email;
}
