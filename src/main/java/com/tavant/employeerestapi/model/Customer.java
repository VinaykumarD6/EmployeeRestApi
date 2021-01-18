package com.tavant.employeerestapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="customers")
public class Customer {
	@Id
	private Integer customerNumber;
	@NotBlank(message = "customerName should not be blank")
	private String customerName;
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "ContactLastName should not be blank")
	private String contactLastName;
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "ContactFirstName should not be blank")

	private String contactFirstName;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "city should not be blank")

	private String city;
	private String state;
	private String postalCode;
	private String country;
	private int salesRepEmployeeNumber;
	private double creditLimit;
//	@OneToMany(mappedBy = "customerNumber" , fetch = FetchType.LAZY)
//	@ToString.Exclude
//	private List<Payment> payments;

}
