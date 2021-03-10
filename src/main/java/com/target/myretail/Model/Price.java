package com.target.myretail.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Price Data model
 */
@Getter
@Setter
public class Price {

    @Field(value = "value")
    @JsonProperty(value = "value")
    private double value;

    @Field(value = "currencyCode")
    @JsonProperty(value = "currencyCode")
    private String currencyCode;

}
