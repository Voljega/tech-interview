package com.corp.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class EligibleTransactionVolume {

    //use Integer to handle null cases
    @JsonProperty("min_price")
    private Integer min;
    @JsonProperty("max_price")
    private Integer max;

    public EligibleTransactionVolume(){// Mandatory for deserialization
    }

    public EligibleTransactionVolume(Integer min,Integer max){
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
