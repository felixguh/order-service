package br.com.orderservice.model.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductPayload {

	@EqualsAndHashCode.Include
	@NotNull(message = "{ProductPayload.productNumber.notNull}")
	private Long productNumber;

	@EqualsAndHashCode.Exclude
	@NotNull(message = "{ProductPayload.amount.notNull}")
	@Positive(message = "{ProductPayload.amount.notPositive}")
	private Integer amount;
}
