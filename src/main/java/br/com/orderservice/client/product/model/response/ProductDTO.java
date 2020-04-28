package br.com.orderservice.client.product.model.response;

import java.math.BigDecimal;

import br.com.orderservice.client.product.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private String name;

	private BigDecimal price;
	
	private Category category;
}
