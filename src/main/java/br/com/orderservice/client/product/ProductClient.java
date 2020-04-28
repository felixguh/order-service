package br.com.orderservice.client.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.orderservice.client.product.model.response.ProductDTO;

@FeignClient(name = "product-service", url = "${product-service.url}")
public interface ProductClient {

	@GetMapping(value = "${product-service.endpoints.findProductByProductNumber}")
	ResponseEntity<ProductDTO> findProductByProductNumber(@PathVariable("productNumber") Long productNumber);

}
