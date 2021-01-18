package com.tavant.employeerestapi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class OrderDetail {
	@Id
	private Integer orderDetailId;
	private Integer orderId;
	//@ManyToOne
	//@JoinColumn(name = "productCode")
	@Column(length=15)
	@Size(max=20)
	@NotBlank(message = "lastName should not be blank")
	private String productCode;
	private Integer quantityOrdered;
	private Double priceEach;
	private Integer orderLineNumber;
//	@OneToMany(mappedBy = "orderId" , fetch = FetchType.EAGER)
//	@ToString.Exclude
//	private List<Order> order;


	

}
