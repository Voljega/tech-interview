package com.corp.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Discount {

    @JsonProperty("article_id")
    private long articleId;
    private String type;
    private int value;

    public Discount(){ //Mandatory for deserialization
    }

    public Discount(long articleId, String type, int value){
        this.articleId = articleId;
        this.type = type;
        this.value = value;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
