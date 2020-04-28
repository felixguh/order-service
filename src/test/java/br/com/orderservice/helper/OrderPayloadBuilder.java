package br.com.orderservice.helper;

import br.com.orderservice.model.payload.OrderPayload;

public class OrderPayloadBuilder {

	private OrderPayload dataToMock;

	private OrderPayloadBuilder() {
		this.dataToMock = OrderPayload.builder().customerNumber(1L).products(ProductPayloadBuilder.create().set())
				.build();
	}

	public static OrderPayloadBuilder create() {
		return new OrderPayloadBuilder();
	}

	public OrderPayload now() {
		return this.dataToMock;
	}
}
