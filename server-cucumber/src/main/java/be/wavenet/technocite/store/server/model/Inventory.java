package be.wavenet.technocite.store.server.model;

import java.io.Serializable;

public class Inventory implements Serializable {

    private String id;
    private String marketId;
    private String productId;
    private double price;

    private Inventory() {}

    public Inventory(String id, String marketId, String productId, double price) {
        this.id = id;
        this.marketId = marketId;
        this.productId = productId;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getMarketId() {
        return marketId;
    }

    public String getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
