package com.example.orderup.Objects;

public class Giftcard {
    String number;
    float amount;




    public Giftcard(String number, float amount) {
        this.number = number;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public String getNumber() {
        return number;
    }

}
