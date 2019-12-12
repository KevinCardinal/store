package be.wavenet.technocite.store.server.dto;

import java.io.Serializable;

public class InventoryDto implements Serializable {

    private String id;
    private String marketName;
    private String locality;
    private String productName;
    private String brand;
    private double weight;
    private double price;

    private InventoryDto() {}

    public InventoryDto(String id, String marketName, String locality, String productName, String brand, double weight, double price) {
        this.id = id;
        this.marketName = marketName;
        this.locality = locality;
        this.productName = productName;
        this.brand = brand;
        this.weight = weight;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getMarketName() {
        return marketName;
    }

    public String getLocality() {
        return locality;
    }

    public String getProductName() {
        return productName;
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
