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
	
	public ProductPayloadBuilder withProductNumber(final Long productNumber) {
		this.dataToMock.setProductNumber(productNumber);
		
		return this;
	}
	
	public ProductPayloadBuilder withAmount(final Integer amount) {
		this.dataToMock.setAmount(amount);
		
		return this;
	}
	
	public Set<ProductPayload> set(){
		final var set = new HashSet<ProductPayload>();
		
		set.add(this.dataToMock);
		
		return set;
	}
	
	public ProductPayload now(){
		
		return this.dataToMock;
	}

}
