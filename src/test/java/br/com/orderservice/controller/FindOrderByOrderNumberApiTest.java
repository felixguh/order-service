package br.com.orderservice.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.orderservice.exception.OrderNotExistsException;
import br.com.orderservice.exception.handler.ExceptionHandlerController;
import br.com.orderservice.service.OrderService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrderController.class)
@ContextConfiguration(classes = { OrderController.class, ExceptionHandlerController.class })
public class FindOrderByOrderNumberApiTest {

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private OrderService orderService;

	@Test
	public void shouldFindOrderByOrderNumberAndReturnOk() throws Exception {
		mockmvc.perform(get(Url.ORDER_NUMBER.getUrl()).accept(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void whenFindOrderByOrderNumberWithNotExistsOrderShouldReturnNoContent() throws Exception {
		final var orderNumber = 1L;

		doThrow(new OrderNotExistsException()).when(orderService).findOrderByOrderNumber(orderNumber);

		mockmvc.perform(get(Url.ORDER_NUMBER.getUrl()).accept(APPLICATION_JSON)).andExpect(status().isNoContent());

	}

}
