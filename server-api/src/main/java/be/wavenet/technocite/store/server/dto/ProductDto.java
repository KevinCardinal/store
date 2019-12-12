package be.wavenet.technocite.store.server.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {

    private String id;
    private String name;
    private String brand;
    private double weight;

    private ProductDto() {}

    public ProductDto(String id, String name, String brand, double weight) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getWeight() {
        return weight;
    }
}
