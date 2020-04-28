package br.com.orderservice.helper;

import br.com.orderservice.model.response.OrderResponse;

public class OrderResponseBuilder {

	private OrderResponse dataToMock;

	private OrderResponseBuilder() {
		this.dataToMock = OrderResponse.builder().entity(OrderBuilder.create().now()).build();
	}
	
	public static OrderResponseBuilder create() {
		return new OrderResponseBuilder();
	}
	
	public OrderResponse now() {
		return this.dataToMock;
	}

}
