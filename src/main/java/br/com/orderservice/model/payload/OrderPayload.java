package br.com.orderservice.model.payload;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPayload {

	@NotNull(message = "{OrderPayload.products.notNull}")
	@Valid
	@Size(min = 1, message = "{OrderPayload.products.size}")
	private Set<ProductPayload> products;
	
	@NotNull(message = "{OrderPayload.customerNumber.notNull}")
	private Long customerNumber;
}
