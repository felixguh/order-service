package br.com.orderservice.helper;

import java.util.HashSet;
import java.util.Set;

import br.com.orderservice.model.payload.ProductPayload;

public class ProductPayloadBuilder {
	
	private ProductPayload dataToMock;
	
	private ProductPayloadBuilder() {
		this.dataToMock = ProductPayload.builder().amount(1).productNumber(1L).build();
	}
	
	public static ProductPayloadBuilder create() {
		return new ProductPayloadBuilder();
	}
	
	public Set<ProductPayload> set(){
		final var set = new HashSet<ProductPayload>();
		
		set.add(this.dataToMock);
		
		return set;
	}

}
