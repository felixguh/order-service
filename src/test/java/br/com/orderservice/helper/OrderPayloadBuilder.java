package br.com.orderservice.helper;

import java.util.HashSet;
import java.util.Set;

import br.com.orderservice.model.payload.OrderPayload;
import br.com.orderservice.model.payload.ProductPayload;

public class OrderPayloadBuilder {

	private OrderPayload dataToMock;

	private OrderPayloadBuilder() {
		this.dataToMock = OrderPayload.builder().customerNumber(1L).products(ProductPayloadBuilder.create().set())
				.build();
	}

	public static OrderPayloadBuilder create() {
		return new OrderPayloadBuilder();
	}

	public OrderPayloadBuilder withCustomerNumber(final Long customerNumber) {
		this.dataToMock.setCustomerNumber(customerNumber);

		return this;
	}

	public OrderPayloadBuilder withProducts(final Set<ProductPayload> products) {
		this.dataToMock.setProducts(products);

		return this;
	}

	public OrderPayloadBuilder withProduct(final ProductPayload product) {
		final var set = new HashSet<ProductPayload>();
		set.add(product);

		this.dataToMock.setProducts(set);

		return this;
	}

	public OrderPayload now() {
		return this.dataToMock;
	}

}
