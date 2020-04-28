package br.com.orderservice.model.mapper;

import br.com.orderservice.model.Product;
import br.com.orderservice.model.payload.ProductPayload;

public class ProductMapper {

	private ProductMapper() {

	}

	public static Product toEntity(final ProductPayload payload) {
		return Product.builder().amount(payload.getAmount()).productNumber(payload.getProductNumber()).build();
	}

}
