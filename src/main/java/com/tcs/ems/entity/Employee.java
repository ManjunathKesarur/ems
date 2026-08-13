package com.tcs.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee {
	@Id
	@Email
	private String email;
	
	@NotBlank
	private String name;
	
	@PositiveOrZero     //allows salary with 0 and above
	private Double salary;
	
	@NotBlank
	private String department;
}
