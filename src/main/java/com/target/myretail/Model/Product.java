package com.target.myretail.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Product data model
 */
@Document(collection = "Products")
@Getter
@Setter
public class Product {

    @Id
    @Field(value = "_id")
    @JsonProperty(value = "id")
    private String id;

    @Field(value = "name")
    @JsonProperty(value = "name")
    private String name;

    @Field(value = "currentPrice")
    @JsonProperty(value = "currentPrice")
    private Price currentPrice;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
