package com.tavant.employeerestapi.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
	@Id
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "paymentId should not be blank")
	private Integer paymentId;
//	@ManyToOne
//	@JoinColumn(name = "customerId")
	private Integer customerId;
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "checkNumber should not be blank")
	private String checkNumber;
	private Date paymentDate;
	private double amount;


}
