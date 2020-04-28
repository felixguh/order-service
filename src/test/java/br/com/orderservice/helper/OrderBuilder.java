package br.com.orderservice.helper;

import java.util.Optional;

import br.com.orderservice.model.Order;
import br.com.orderservice.model.mapper.OrderMapper;

public class OrderBuilder {
	
	private Order dataToMock;
	
	private OrderBuilder() {
		this.dataToMock = OrderMapper.toEntity(OrderPayloadBuilder.create().now());
	}
	
	public static OrderBuilder create() {
		return new OrderBuilder();
	}
	
	public Optional<Order> optional(){
		return Optional.of(this.dataToMock);
	}

}
