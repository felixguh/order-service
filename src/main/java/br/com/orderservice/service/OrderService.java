package br.com.orderservice.service;

import org.springframework.stereotype.Service;

import br.com.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository repository;

	public OrderService(final OrderRepository repository) {
		this.repository = repository;
	}


}
