package br.com.orderservice.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import br.com.orderservice.common.model.ErrorResponse;
import br.com.orderservice.model.payload.OrderPayload;
import br.com.orderservice.model.response.OrderResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "OrderApi")
public interface OrderApi {

	@ApiOperation("Create order")
	@ApiResponses({ @ApiResponse(code = 201, message = "Order was created with success"),
			@ApiResponse(code = 400, message = "Wrong information was sent", response = ErrorResponse.class),
			@ApiResponse(code = 412, message = "Product not found or Customer not found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred", response = ErrorResponse.class) })
	ResponseEntity<OrderResponse> create(@Valid OrderPayload payload);
	
	@ApiOperation("Find order by orderNumber")
	@ApiResponses({ @ApiResponse(code = 200, message = "Order was found with success"),
			@ApiResponse(code = 204, message = "Order not exists"),
			@ApiResponse(code = 400, message = "Wrong information was sent", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred", response = ErrorResponse.class) })
	OrderResponse findByOrderNumber(
			@ApiParam(value = "Order number", required = true, example = "1") final Long orderNumber);
	
	

}
