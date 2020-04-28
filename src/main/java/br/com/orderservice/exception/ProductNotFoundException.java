package br.com.orderservice.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6641091966632620533L;
	
	private Long productNumber;
	
	public ProductNotFoundException(final Long productNumber) {
		super();
		this.productNumber = productNumber;
	}

}
