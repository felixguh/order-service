package br.com.orderservice.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.orderservice.exception.CustomerServiceNotFoundException;
import br.com.orderservice.exception.ProductNotFoundException;
import br.com.orderservice.exception.handler.ExceptionHandlerController;
import br.com.orderservice.helper.OrderPayloadBuilder;
import br.com.orderservice.helper.OrderResponseBuilder;
import br.com.orderservice.helper.ProductPayloadBuilder;
import br.com.orderservice.model.payload.OrderPayload;
import br.com.orderservice.model.payload.ProductPayload;
import br.com.orderservice.service.OrderService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrderController.class)
@ContextConfiguration(classes = { OrderController.class, ExceptionHandlerController.class })
public class CreateOrderApiTest {

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private OrderService orderService;

	@Test
	public void shouldCreateOrderAndReturnCreated() throws Exception {
		final var payload = OrderPayloadBuilder.create().now();

		when(orderService.create(payload)).thenReturn(OrderResponseBuilder.create().now());

		callPost(payload, 201);
	}

	@Test
	public void whenCreateOrderWithNotExistsCustomerShouldReturnPreConditionFailed() throws Exception {
		final var payload = OrderPayloadBuilder.create().now();

		doThrow(new CustomerServiceNotFoundException()).when(orderService).create(payload);

		callPost(payload, 412);
	}

	@Test
	public void whenCreateOrderWithNotExistsProductShouldReturnPreConditionFailed() throws Exception {
		final var payload = OrderPayloadBuilder.create().now();

		final var product = payload.getProducts().stream().findAny();

		if (product.isPresent())
			doThrow(new ProductNotFoundException(product.get().getProductNumber())).when(orderService).create(payload);

		callPost(payload, 412);
	}

	@Test
	public void whenCreateOrderWithNullCustomerShouldReturnBadRequest() throws Exception {
		final var payload = OrderPayloadBuilder.create().withCustomerNumber(null).now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateOrderWithNullProductsShouldReturnBadRequest() throws Exception {
		final var payload = OrderPayloadBuilder.create().withProducts(null).now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateOrderWithEmptyProductsShouldReturnBadRequest() throws Exception {
		final var payload = OrderPayloadBuilder.create().withProducts(new HashSet<ProductPayload>()).now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateOrderWithNullProductNumberShouldReturnBadRequest() throws Exception {
		final var payload = OrderPayloadBuilder.create()
				.withProduct(ProductPayloadBuilder.create().withProductNumber(null).now()).now();

		callPost(payload, 400);
	}
	
	@Test
	public void whenCreateOrderWithNullAmountShouldReturnBadRequest() throws Exception {
		final var payload = OrderPayloadBuilder.create()
				.withProduct(ProductPayloadBuilder.create().withAmount(null).now()).now();

		callPost(payload, 400);
	}
	
	@Test
	public void whenCreateOrderWithNegativeAmountShouldReturnBadRequest() throws Exception {
		final var payload = OrderPayloadBuilder.create()
				.withProduct(ProductPayloadBuilder.create().withAmount(-1).now()).now();

		callPost(payload, 400);
	}

	private void callPost(final OrderPayload payload, final int status) throws Exception {
		mockmvc.perform(
				post(Url.BASE_URL.getUrl()).contentType(APPLICATION_JSON).content(mapper.writeValueAsString(payload)))
				.andExpect(status().is(status));
	}

}
