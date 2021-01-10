package br.com.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Categoria {
    @Id
    private String id;
    private String name;

    public Categoria(String name) {
        this.name = name;
    }

    
}
