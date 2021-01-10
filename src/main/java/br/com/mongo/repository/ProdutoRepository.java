package br.com.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.mongo.entity.Produto;

public interface ProdutoRepository extends MongoRepository<Produto,String> {
    
}
