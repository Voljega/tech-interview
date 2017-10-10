package com.corp.model;

import com.corp.store.DataStore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartTest {

    @Test
    public void ShouldComputeCartCorrectly() {
        DataStore dataStore = new DataStore();
        dataStore.getData().getArticles().add(new Article(1, "article1", 10));
        dataStore.getData().getArticles().add(new Article(2, "article2", 5));
        dataStore.getData().getArticles().add(new Article(3, "article3", 1));
        dataStore.getData().sortArticles();//not very nice

        Cart cart = new Cart();
        cart.getItems().add(new Item(1, 5));
        cart.getItems().add(new Item(2, 2));
        cart.getItems().add(new Item(3, 4));
        //total should be 5*10+5*2+1*4 = 64

        cart.computeCart(dataStore);
        assertEquals(64, cart.getTotal());
    }

    @Test
    public void ShouldComputeCartCorrectlyWithUnexistingArticle() {
        DataStore dataStore = new DataStore();
        dataStore.getData().getArticles().add(new Article(1, "article1", 10));
        dataStore.getData().getArticles().add(new Article(2, "article2", 5));
        dataStore.getData().getArticles().add(new Article(3, "article3", 1));
        dataStore.getData().sortArticles();//not very nice

        Cart cart = new Cart();
        cart.getItems().add(new Item(1, 5));
        cart.getItems().add(new Item(2, 2));
        cart.getItems().add(new Item(3, 4));
        cart.getItems().add(new Item(5, 4));
        //total should be 5*10+5*2+0.5*4 = 64

        cart.computeCart(dataStore);
        assertEquals(64, cart.getTotal());
    }

    @Test
    public void ShouldComputeCartCorrectlyWithNoArticle() {
        DataStore dataStore = new DataStore();
        dataStore.getData().getArticles().add(new Article(1, "article1", 10));
        dataStore.getData().getArticles().add(new Article(2, "article2", 5));
        dataStore.getData().getArticles().add(new Article(3, "article3", 1));
        dataStore.getData().sortArticles();//not very nice

        Cart cart = new Cart();
        //total should be 0

        cart.computeCart(dataStore);
        assertEquals(0, cart.getTotal());
    }
}
