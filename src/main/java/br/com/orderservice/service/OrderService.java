package br.com.orderservice.service;

import static java.math.BigDecimal.ZERO;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.orderservice.client.customer.CustomerClient;
import br.com.orderservice.client.product.ProductClient;
import br.com.orderservice.exception.CustomerServiceNotFoundException;
import br.com.orderservice.exception.ProductNotFoundException;
import br.com.orderservice.model.Order;
import br.com.orderservice.model.Product;
import br.com.orderservice.model.mapper.OrderMapper;
import br.com.orderservice.model.payload.OrderPayload;
import br.com.orderservice.model.response.OrderResponse;
import br.com.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository repository;
	private final CustomerClient customerClient;
	private final ProductClient productClient;

	@Autowired
	public OrderService(final OrderRepository repository, final CustomerClient customerClient,
			final ProductClient productClient) {
		
		this.repository = repository;
		this.customerClient = customerClient;
		this.productClient = productClient;
	}

	public OrderResponse create(final OrderPayload payload) {
		call(() -> customerClient.findCustomerByCustomerNumber(payload.getCustomerNumber()),
				new CustomerServiceNotFoundException());

		final var entity = repository.save(prepareOrderToSave(payload));

		return OrderResponse.builder().entity(entity).build();
	}

	private Order prepareOrderToSave(final OrderPayload payload) {
		final var entity = OrderMapper.toEntity(payload);

		final var total = sumTotal(entity.getProducts());

		entity.setTotal(total);
		
		return entity;
	}

	private BigDecimal sumTotal(final Collection<Product> product) {
		var total = ZERO;
		
		for (var p : product)
			total = total.add(getProductPrice(p.getProductNumber()).multiply(new BigDecimal(p.getAmount())));
			
		return total;
	}

	private <T> T call(final Supplier<ResponseEntity<T>> supplier, final RuntimeException exception) {
		final var response = supplier.get();

		if (response.getStatusCode() == NO_CONTENT)
			throw exception;

		return response.getBody();
	}

	private BigDecimal getProductPrice(final Long productNumber) {
		final var product = call(() -> productClient.findProductByProductNumber(productNumber),
				new ProductNotFoundException(productNumber));

		return product.getPrice();
	}
}
