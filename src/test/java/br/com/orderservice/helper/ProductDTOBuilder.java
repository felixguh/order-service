package br.com.orderservice.helper;

import static br.com.orderservice.client.product.model.enums.Category.FOODS;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.orderservice.client.product.model.response.ProductDTO;

public class ProductDTOBuilder {

	private ProductDTO dataToMock;

	private ProductDTOBuilder() {
		this.dataToMock = ProductDTO.builder().category(FOODS).name("Batata").price(BigDecimal.valueOf(20.00)).build();
	}

	public static ProductDTOBuilder create() {
		return new ProductDTOBuilder();
	}

	public ResponseEntity<ProductDTO> responseEntity(final HttpStatus status) {
		return new ResponseEntity<ProductDTO>(this.dataToMock, status);
	}

}
