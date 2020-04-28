package br.com.orderservice.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.orderservice.exception.CustomerServiceNotFoundException;
import br.com.orderservice.exception.OrderNotExistsException;
import br.com.orderservice.exception.ProductNotFoundException;
import br.com.orderservice.helper.CustomerDTOBuilder;
import br.com.orderservice.helper.OrderBuilder;
import br.com.orderservice.helper.ProductDTOBuilder;
import br.com.orderservice.model.Order;

@RunWith(SpringRunner.class)
public class OrderServiceTest extends OrderAttributes {

	@Test
	public void shouldCreateOrder() {
		final var productDTO = ProductDTOBuilder.create().responseEntity(OK);

		when(customerClient.findCustomerByCustomerNumber(CUSTOMER_NUMBER)).thenReturn(CUSTOMER_DTO);

		when(productClient.findProductByProductNumber(PRODUCT_NUMBER)).thenReturn(productDTO);

		service.create(ORDER_PAYLOAD);

		verify(customerClient).findCustomerByCustomerNumber(CUSTOMER_NUMBER);
		verify(productClient).findProductByProductNumber(PRODUCT_NUMBER);
	}

	@Test(expected = CustomerServiceNotFoundException.class)
	public void whenCreateOrderWithNotExistsCustomerShouldReturnCustomerServiceNotFoundException() {
		final var customerDTO = CustomerDTOBuilder.create().responseEntity(NO_CONTENT);

		when(customerClient.findCustomerByCustomerNumber(CUSTOMER_NUMBER)).thenReturn(customerDTO);

		service.create(ORDER_PAYLOAD);
	}

	@Test(expected = ProductNotFoundException.class)
	public void whenCreateOrderWithNotExistsProductShouldReturnProductNotFoundException() {
		final var productDTO = ProductDTOBuilder.create().responseEntity(NO_CONTENT);

		when(customerClient.findCustomerByCustomerNumber(CUSTOMER_NUMBER)).thenReturn(CUSTOMER_DTO);

		final var product = ORDER_PAYLOAD.getProducts().stream().findFirst();

		if (product.isPresent())
			when(productClient.findProductByProductNumber(product.get().getProductNumber())).thenReturn(productDTO);

		service.create(ORDER_PAYLOAD);
	}

	@Test
	public void shouldFindOrderByOrderNumber() {
		final var orderOptional = OrderBuilder.create().optional();

		theSameToFind(orderOptional);

		verify(repository).findByOrderNumber(ORDER_NUMBER);
	}

	private void theSameToFind(final Optional<Order> orderOptional) {
		when(repository.findByOrderNumber(ORDER_NUMBER)).thenReturn(orderOptional);

		service.findOrderByOrderNumber(ORDER_NUMBER);
	}

	@Test(expected = OrderNotExistsException.class)
	public void whenFindOrderByOrderNumberWithNotExistsOrderShouldReturnOrderNotExistsException() {
		theSameToFind(Optional.empty());
	}

}
