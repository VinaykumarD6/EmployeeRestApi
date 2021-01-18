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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order{
	@Id
//	@ManyToOne
//	@JoinColumn(name = "orderId")
	private Integer orderId;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "status should not be blank")
	private String status;
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "customerId should not be blank")
	private String customerId;

}