package br.com.orderservice.client.product.model.response;

import java.math.BigDecimal;

import br.com.orderservice.client.product.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private String name;

	private BigDecimal price;
	
	private Category category;
}
