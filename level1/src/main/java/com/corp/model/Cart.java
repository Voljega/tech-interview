package com.corp.model;

import com.corp.store.DataStore;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Cart {

    private long id;//better use long for ids

    //Better use set with Item impl. Comparable but easier for testing
    private List<Item> items = new ArrayList<>();

    private int total;//prices are in cent

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore //ignored for serialization
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty //used for deserialization
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void computeCart( DataStore dataStore){
        int total = 0;
        for (Item item : this.getItems()){
            Article article = dataStore.getData().getArticlesById(item.getArticleId());
            double price = article!=null?article.getPrice():0;
            total += price * item.getQuantity();
        }
        setTotal(total);
    }
}
