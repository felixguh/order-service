package br.com.orderservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.orderservice.model.Order;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {

}
