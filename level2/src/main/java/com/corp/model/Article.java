package com.corp.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Article {

    private long id;//better use long for ids

    private String name;

    private int price;//double for price

    public long getId() {
        return id;
    }

    public Article(){}

    public Article(long id, String name, int price){
        this.id = id;
        this.name = name;
        this.price =price;
    }

    //@JsonSetter("id")
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
