package com.corp.model;

import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Item {

    @JsonProperty("article_id")
    private long articleId; //better use long for ids
    private int quantity;

    public Item(){}

    public Item(long articleId,int quantity){
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
