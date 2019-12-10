package be.wavenet.technocite.store.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class InventoryProductDto implements Serializable {

    @JsonProperty("id")
    private String inventoryId;
    private String name;
    private String brand;
    private double weight;
    private double price;

    private InventoryProductDto() {}

    public InventoryProductDto(String inventoryId, String name, String brand, double weight, double price) {
        this.inventoryId = inventoryId;
        this.name = name;
        this.brand = brand;
        this.weight = weight;
        this.price = price;
    }

    public String getInventoryId() {
        return inventoryId;
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

    public double getPrice() {
        return price;
    }
}
