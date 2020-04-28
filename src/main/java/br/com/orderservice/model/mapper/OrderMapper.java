package br.com.orderservice.model.mapper;

import java.util.stream.Collectors;

import br.com.orderservice.model.Order;
import br.com.orderservice.model.payload.OrderPayload;

public class OrderMapper {

	private OrderMapper() {

	}

	public static Order toEntity(final OrderPayload payload) {
		return Order.builder().customerNumber(payload.getCustomerNumber())
				.products(
						payload.getProducts().stream().map(p -> ProductMapper.toEntity(p)).collect(Collectors.toList()))
				.build();
	}

}
