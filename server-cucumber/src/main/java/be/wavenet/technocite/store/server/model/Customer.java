package be.wavenet.technocite.store.server.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Customer implements Serializable {

    private String id;
    private String name;
    private double balance;
    private LocalDate registration;

    private Customer() {}

    public Customer(String id, String name, double balance, LocalDate registration) {
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

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
