package br.com.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.mongo.entity.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}
