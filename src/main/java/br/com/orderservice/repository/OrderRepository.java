package br.com.orderservice.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.orderservice.model.Order;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {
	
	Optional<Order> findByOrderNumber(final Long orderNumber);

}
