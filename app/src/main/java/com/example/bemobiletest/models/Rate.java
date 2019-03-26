package com.example.bemobiletest.models;

public class Rate {

    private String from;
    private String to;
    private float rate;

    public Rate(String from, String to, float rate){
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public float getRate() {
        return rate;
    }
}
