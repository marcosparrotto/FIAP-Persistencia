package br.com.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.mongo.entity.Pessoa;

public interface PessoaRepository extends MongoRepository<Pessoa,String> {
    
}
