package com.corp.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class DeliveryFee {

    @JsonProperty("eligible_transaction_volume")
    private EligibleTransactionVolume eligibleTransactionVolume;
    private int price; //cents again, not need for double

    public DeliveryFee(){//mandatory for deserialization
    }

    public DeliveryFee(EligibleTransactionVolume e,int price){
        this.eligibleTransactionVolume = e;
        this.price= price;
    }

    public EligibleTransactionVolume getEligibleTransactionVolume() {
        return eligibleTransactionVolume;
    }

    public void setEligibleTransactionVolume(EligibleTransactionVolume eligibleTransactionVolume) {
        this.eligibleTransactionVolume = eligibleTransactionVolume;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
