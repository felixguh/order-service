package br.com.orderservice.model.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPayload {

	private Set<ProductPayload> products;
	
	private Long customerNumber;
}
