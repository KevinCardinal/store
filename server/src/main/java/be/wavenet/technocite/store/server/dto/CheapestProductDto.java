package be.wavenet.technocite.store.server.dto;

import java.io.Serializable;

public class CheapestProductDto implements Serializable {

    private String market;
    private String locality;
    private String brand;
    private String product;
    private double unitPrice;

    private CheapestProductDto() {}

    public CheapestProductDto(String market, String locality, String brand, String product, double unitPrice) {
        this.market = market;
        this.locality = locality;
        this.brand = brand;
        this.product = product;
        this.unitPrice = unitPrice;
    }

    public String getMarket() {
        return market;
    }

    public String getLocality() {
        return locality;
    }

    public String getBrand() {
        return brand;
    }

    public String getProduct() {
        return product;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
