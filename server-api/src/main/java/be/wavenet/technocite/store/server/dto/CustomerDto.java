package be.wavenet.technocite.store.server.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerDto implements Serializable {

    private String id;
    private String name;
    private double balance;
    private LocalDate registration;

    private CustomerDto() {}

    public CustomerDto(String id, String name, double balance, LocalDate registration) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.registration = registration;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDate getRegistration() {
        return registration;
    }
}
