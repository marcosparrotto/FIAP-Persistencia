package br.com.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.mongo.entity.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {

}
