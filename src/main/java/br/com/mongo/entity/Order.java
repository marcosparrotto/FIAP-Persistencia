package br.com.mongo.entity;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.mongo.enums.OrderStatus;
import lombok.Data;

@Document
@Data
public class Order {

    @Id
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    public void setOrderStatus(OrderStatus status) {
        this.orderStatus = status.getCode();
    }

    private List<Produto> produtos;

    public Order(Instant moment, OrderStatus orderStatus, List<Produto> produtos) {
        this.moment = moment;
        this.orderStatus = orderStatus.getCode();
        this.produtos = produtos;
    }

    public Order(Instant moment, Integer orderStatus, List<Produto> produtos) {
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.produtos = produtos;
    }

    public Order() {
    }

}
