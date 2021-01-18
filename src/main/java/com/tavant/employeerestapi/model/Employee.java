package com.tavant.employeerestapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employees")
public class Employee  {
	

	
	@Id
	private Integer employeeNumber;
	//@Column(name="fname")
	
	@NotBlank(message = "firstName should not be blank")
	private String firstName;
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "lastName should not be blank")
	private String lastName;
	@NotBlank(message = "extention should has to be provided")
	@javax.persistence.Transient	
	private String extention;
	@NotBlank(message = "email should not blank")
	@Email(message = "email should be valid")
	private String email;
//	@ManyToOne
//	@JoinColumn(name = "officeCode")
	@NotNull(message="office code should not blank")
	@Max(123)
	@Min(100)
	private Integer officeCode;
	private Integer reportsTo;
	@Column(length=15)
	@Size(max=20)
	private String jobTitle;
	
	
	
	
}