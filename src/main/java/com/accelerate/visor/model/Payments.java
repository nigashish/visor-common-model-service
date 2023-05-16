package com.accelerate.visor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYMENT_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payments {
	@Id
	@GeneratedValue
	private Long paymentId;
	private String PaymentStatus;
	private String trantactionId;
	private Long orderId;
	private Double amount;

}
