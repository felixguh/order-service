package br.com.orderservice.listener;


import static br.com.orderservice.model.Order.ORDER_SEQUENCE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import br.com.orderservice.model.Order;
import br.com.orderservice.service.SequenceGeneratorService;

@Component
public class OrderListener extends AbstractMongoEventListener<Order> {

	private final SequenceGeneratorService sequenceGenerator;

	@Autowired
	public OrderListener(SequenceGeneratorService sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Order> event) {
		if (event.getSource().getOrderNumber() == null)
			event.getSource().setOrderNumber(sequenceGenerator.generateSequence(ORDER_SEQUENCE));
	}

}

