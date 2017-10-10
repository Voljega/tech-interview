package com.corp.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement
public class Data {

    //best to use TreeSet with Article impl. Comparable but easier for testing
    private List<Article> articles = new Vector<>();//threadsafe
    //best to use TreeSet with Cart impl. Comparable but easier for testing
    private List<Cart> carts = new Vector<>();//threadsafe
    private List<DeliveryFee> deliveryFees = new Vector();

    //Better handling of articles, use TreeMap for best traversal times
    //don't allow external access
    @JsonIgnore
    private Map<Long,Article> articlesById = new TreeMap<>();

    @JsonIgnore //ignored for serialization
    public List<Article> getArticles() {
        return articles;
    }

    @JsonProperty //used for deserialization
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Article getArticlesById(long id) {
        return articlesById.get(id);
    }

    @JsonIgnore //ignored for serialization
    public List<DeliveryFee> getDeliveryFees() {
        return deliveryFees;
    }

    @JsonProperty("delivery_fees") //used for deserialization
    public void setDeliveryFees(List<DeliveryFee> deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public void sortArticles(){
        if (articles!=null){
            articles.stream().forEach(a -> articlesById.put(a.getId(),a));
        }
    }
}
