package com.example.orderup.Objects;

/**
 * This class holds a single Giftcard object.
 */
public class Giftcard {
    String number;
    float amount;

    /**
     * Constructor.
     *
     * @param number the gift card 16 digit numeric code.
     * @param amount the dollar amount on the giftcard.
     */
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
