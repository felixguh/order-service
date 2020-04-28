package br.com.orderservice.service;

import static org.springframework.http.HttpStatus.OK;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import br.com.orderservice.client.customer.CustomerClient;
import br.com.orderservice.client.customer.model.response.CustomerDTO;
import br.com.orderservice.client.product.ProductClient;
import br.com.orderservice.helper.CustomerDTOBuilder;
import br.com.orderservice.helper.OrderPayloadBuilder;
import br.com.orderservice.model.payload.OrderPayload;
import br.com.orderservice.repository.OrderRepository;

public class OrderAttributes {

	@Mock
	protected OrderRepository repository;

	@Mock
	protected CustomerClient customerClient;

	@Mock
	protected ProductClient productClient;

	@InjectMocks
	protected OrderService service;

	protected static final OrderPayload ORDER_PAYLOAD = OrderPayloadBuilder.create().now();

	protected static final Long CUSTOMER_NUMBER = 1L;

	protected static final Long PRODUCT_NUMBER = 1L;
	
	protected static final Long ORDER_NUMBER = 1L;

	protected static final ResponseEntity<CustomerDTO> CUSTOMER_DTO = CustomerDTOBuilder.create().responseEntity(OK);

}
