package br.com.orderservice.model;

import java.math.BigDecimal;
import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	@Transient
	public static final String ORDER_SEQUENCE = "order_sequence";
	
	@Id
	private ObjectId id;
	
	private Long orderNumber;

	private Long customerNumber;
	
	private Collection<Product> products;
	
	private BigDecimal total;

}
