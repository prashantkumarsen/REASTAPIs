package com.reastApis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddStudentRequestSDto 
{
	@NotNull(message = "Name cannot be null")
    @Size(min = 3, message = "Name must be at least 3 characters")
	private String name;
	
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$", message = "Email must match format")
	private String email;
}
