package com.demo.product.search.domain;

import java.util.Map;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "catalog", type = "product")
public class Product {

  private static final String NAME = "name";
  private static final String DESCRIPTION = "description";
  private static final String MANUFACTURER = "manufacturer";
  private static final String PRICE = "price";

  @Id
  @NotNull
  private String id;
  @Field
  @NotNull
  private String name;
  @Field
  private String description;
  @Field
  private String manufacturer;
  @Field
  @NotNull
  private Double price;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void update(Map<String, Object> dataMap) {
    if (dataMap != null) {
      dataMap.keySet().forEach(t -> {
        switch(t.toLowerCase()) {
          case NAME:
            setName((String) dataMap.get(t));
            break;
          case MANUFACTURER:
            setManufacturer((String) dataMap.get(t));
            break;
          case DESCRIPTION:
            setDescription((String) dataMap.get(t));
            break;
          case PRICE:
            setPrice((Double) dataMap.get(t));
            break;
        }
      });
    }
  }

}