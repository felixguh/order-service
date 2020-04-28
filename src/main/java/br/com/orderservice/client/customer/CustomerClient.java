package br.com.orderservice.client.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.orderservice.client.customer.model.response.CustomerDTO;

@FeignClient(name = "customer-service", url = "${customer-service.url}")
public interface CustomerClient {

	@GetMapping(value = "${customer-service.endpoints.findCustomerByCustomerNumber}")
	ResponseEntity<CustomerDTO> findCustomerByCustomerNumber(@PathVariable("customerNumber") Long customerNumber);
}
