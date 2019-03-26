package com.example.bemobiletest.models;

import java.io.Serializable;

public class Transaction implements Serializable {

    private String sku;
    private float amount;
    private String currency;
    private float rateToEuro;

    public Transaction(String sku, float amount, String currency, float rateToEuro){
        this.sku = sku;
        this.amount = amount;
        this.currency = currency;
        this.rateToEuro = rateToEuro;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getRateToEuro() {
        return rateToEuro;
    }

    public void setRateToEuro(float rate) {
        this.rateToEuro = rate;
    }
}
