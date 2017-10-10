package com.corp.model;

import com.corp.store.DataStore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartTest {

    @Test
    public void ShouldComputeCartCorrectlyWithNoFees() {
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
    public void ShouldComputeCartCorrectlyWithUnexistingArticleWithNoFees() {
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
    public void ShouldComputeCartCorrectlyWithNoArticleWithNoFees() {
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

    @Test
    public void ShouldComputeCartCorrectlyWithNoArticleButWithFees() {
        DataStore dataStore = new DataStore();
        dataStore.getData().getArticles().add(new Article(1, "article1", 10));
        dataStore.getData().getArticles().add(new Article(2, "article2", 5));
        dataStore.getData().getArticles().add(new Article(3, "article3", 1));
        dataStore.getData().sortArticles();//not very nice
        DeliveryFee d1 = new DeliveryFee(new EligibleTransactionVolume(0,5),100);
        DeliveryFee d2 = new DeliveryFee(new EligibleTransactionVolume(5,10),50);
        DeliveryFee d3 = new DeliveryFee(new EligibleTransactionVolume(10,20),25);
        dataStore.getData().getDeliveryFees().add(d1);
        dataStore.getData().getDeliveryFees().add(d2);
        dataStore.getData().getDeliveryFees().add(d3);

        Cart cart = new Cart();
        //total should be 0

        cart.computeCart(dataStore);
        assertEquals(100, cart.getTotal());
    }

    @Test
    public void ShouldComputeCartCorrectlyWithFees() {
        DataStore dataStore = new DataStore();
        dataStore.getData().getArticles().add(new Article(1, "article1", 10));
        dataStore.getData().getArticles().add(new Article(2, "article2", 5));
        dataStore.getData().getArticles().add(new Article(3, "article3", 1));
        dataStore.getData().sortArticles();//not very nice
        DeliveryFee d1 = new DeliveryFee(new EligibleTransactionVolume(0,100),100);
        DeliveryFee d2 = new DeliveryFee(new EligibleTransactionVolume(100,200),50);
        DeliveryFee d3 = new DeliveryFee(new EligibleTransactionVolume(200,300),25);
        dataStore.getData().getDeliveryFees().add(d1);
        dataStore.getData().getDeliveryFees().add(d2);
        dataStore.getData().getDeliveryFees().add(d3);

        Cart cart = new Cart();
        cart.getItems().add(new Item(1, 5));
        cart.getItems().add(new Item(2, 2));
        cart.getItems().add(new Item(3, 4));
        //total should be 5*10+5*2+1*4 = 64 + 100 for fees

        cart.computeCart(dataStore);
        assertEquals(164, cart.getTotal());
    }

    @Test
    public void ShouldComputeCartCorrectlyWithFeesAndDiscount() {
        DataStore dataStore = new DataStore();
        dataStore.getData().getArticles().add(new Article(1, "article1", 100));
        dataStore.getData().getArticles().add(new Article(2, "article2", 50));
        dataStore.getData().getArticles().add(new Article(3, "article3", 10));
        dataStore.getData().sortArticles();//not very nice
        DeliveryFee d1 = new DeliveryFee(new EligibleTransactionVolume(0,100),100);
        DeliveryFee d2 = new DeliveryFee(new EligibleTransactionVolume(100,200),50);
        DeliveryFee d3 = new DeliveryFee(new EligibleTransactionVolume(200,300),25);
        dataStore.getData().getDeliveryFees().add(d1);
        dataStore.getData().getDeliveryFees().add(d2);
        dataStore.getData().getDeliveryFees().add(d3);
        dataStore.getData().getDiscounts().add(new Discount(1,"amount",2));
        dataStore.getData().getDiscounts().add(new Discount(2,"percentage",20));
        dataStore.getData().getDiscounts().add(new Discount(3,"percentage",10));

        Cart cart = new Cart();
        cart.getItems().add(new Item(1, 5));
        cart.getItems().add(new Item(2, 2));
        cart.getItems().add(new Item(3, 4));
        //Total should be :
        //      5*(100-2)               -> 490
        // +    2*(50)*80/100           ->  80
        // +    4*(10)*90/100           ->  36
        // =    606 + 25 for fees       -> 631

        cart.computeCart(dataStore);
        assertEquals(631, cart.getTotal());
    }

    @Test
    public void ShouldComputeCartCorrectlyWithUnexistingArticleAndFees() {
        DataStore dataStore = new DataStore();
        dataStore.getData().getArticles().add(new Article(1, "article1", 10));
        dataStore.getData().getArticles().add(new Article(2, "article2", 5));
        dataStore.getData().getArticles().add(new Article(3, "article3", 1));
        dataStore.getData().sortArticles();//not very nice
        DeliveryFee d1 = new DeliveryFee(new EligibleTransactionVolume(0,100),100);
        DeliveryFee d2 = new DeliveryFee(new EligibleTransactionVolume(100,200),50);
        DeliveryFee d3 = new DeliveryFee(new EligibleTransactionVolume(200,300),25);
        dataStore.getData().getDeliveryFees().add(d1);
        dataStore.getData().getDeliveryFees().add(d2);
        dataStore.getData().getDeliveryFees().add(d3);

        Cart cart = new Cart();
        cart.getItems().add(new Item(1, 5));
        cart.getItems().add(new Item(2, 2));
        cart.getItems().add(new Item(3, 4));
        cart.getItems().add(new Item(5, 4));
        //total should be 5*10+5*2+1*4 = 64 + 100 for fees

        cart.computeCart(dataStore);
        assertEquals(164, cart.getTotal());
    }
}
