package br.com.orderservice.model.response;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.orderservice.model.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Order data")
public class OrderResponse {

	@JsonIgnore
	private final Order entity;

	@ApiModelProperty(value = "Order number", example = "1")
	public Long getOrderNumber() {
		return entity.getOrderNumber();
	}

	@ApiModelProperty(value = "Customer number", example = "1")
	public Long getCustomerNumber() {
		return entity.getCustomerNumber();
	}

	@ApiModelProperty(value = "Products of order")
	public Collection<ProductResponse> getProducts() {
		return entity.getProducts().stream().map(ProductResponse::new).collect(Collectors.toList());
	}

	@ApiModelProperty(value = "Total of order", example = "200.00")
	public BigDecimal getTotal() {
		return entity.getTotal();
	}

}
