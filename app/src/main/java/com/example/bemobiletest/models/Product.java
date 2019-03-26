package com.example.bemobiletest.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    private String id;
    private ArrayList<Transaction> transactions;
    private float totalEuros;

    public Product(String id) {
        this.id = id;
        transactions = new ArrayList<>();
        totalEuros = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getTotalEuros() {
        return totalEuros;
    }

    public void setTotalEuros(float totalEuros) {
        this.totalEuros = totalEuros;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }


}
