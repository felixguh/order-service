package br.com.orderservice.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.orderservice.model.payload.OrderPayload;
import br.com.orderservice.model.response.OrderResponse;
import br.com.orderservice.service.OrderService;

@RequestMapping("/v1/orders")
@RestController
@CrossOrigin(origins = "*")
public class OrderController implements OrderApi {

	private final OrderService service;

	public OrderController(final OrderService service) {
		this.service = service;
	}

	@PostMapping
	@Override
	public ResponseEntity<OrderResponse> create(@RequestBody OrderPayload payload) {

		service.create(payload);

		return null;
	}

	@GetMapping("/order/{orderNumber}")
	@Override
	public OrderResponse findByProductNumber(@PathVariable final Long productNumber) {
		return null;
	}

}
