package br.com.orderservice.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.orderservice.model.Product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Product data")
public class ProductResponse {
	
	@JsonIgnore
	private final Product entity;
	
	@ApiModelProperty(value = "Product number")
	public Long getProductNumber() {
		return entity.getProductNumber();
	}
	
	@ApiModelProperty(value = "Amount of product")
	public Integer getAmount() {
		return entity.getAmount();
	}

}
