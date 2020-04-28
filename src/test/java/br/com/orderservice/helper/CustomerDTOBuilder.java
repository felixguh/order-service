package br.com.orderservice.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.orderservice.client.customer.model.response.CustomerDTO;

public class CustomerDTOBuilder {
	
	private CustomerDTO dataToMock;
	
	private CustomerDTOBuilder() {
		this.dataToMock = CustomerDTO.builder().name("Jhon Doe").build();
	}
	
	public static CustomerDTOBuilder create() {
		return new CustomerDTOBuilder();
	}
	
	public ResponseEntity<CustomerDTO> responseEntity(final HttpStatus status) {
		return new ResponseEntity<CustomerDTO>(this.dataToMock, status);
	}

}
