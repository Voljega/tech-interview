package com.corp.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartDiscountTest {

    @Test
    public void ZeroDiscountForProductWithNoDiscount(){
        Article article = new Article(5, "article5", 10);

        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(1,"amount",20));
        discounts.add(new Discount(2,"percentage",20));
        discounts.add(new Discount(3,"percentage",10));

        Cart cart = new Cart();
        int discount = cart.getDiscount(article,discounts);

        assertEquals(0,discount);
    }

    @Test
    public void ZeroDiscountForProductWithDiscountOfUnkownType(){
        Article article = new Article(2, "article3", 10);

        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(1,"amount",20));
        discounts.add(new Discount(2,"unknoooown",20));
        discounts.add(new Discount(3,"percentage",10));

        Cart cart = new Cart();
        int discount = cart.getDiscount(article,discounts);

        assertEquals(0,discount);
    }

    @Test
    public void DirectCutDiscountForProductWithAmountDiscount(){
        Article article = new Article(2, "article2", 100);

        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(1,"amount",20));
        discounts.add(new Discount(2,"percentage",20));
        discounts.add(new Discount(3,"percentage",10));

        Cart cart = new Cart();
        int discount = cart.getDiscount(article,discounts);

        assertEquals(20,discount);
    }

    @Test
    public void PercentageCutDiscountForProductWithPercentageDiscount(){
        Article article = new Article(2, "article2", 10);

        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(1,"amount",20));
        discounts.add(new Discount(2,"percentage",20));
        discounts.add(new Discount(3,"percentage",10));

        Cart cart = new Cart();
        int discount = cart.getDiscount(article,discounts);

        assertEquals(2,discount);
    }

    @Test
    public void PercentageCutDiscountWithDecimalShouldBeRoundedUp(){
        Article article = new Article(2, "article2", 999);

        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(1,"amount",20));
        discounts.add(new Discount(2,"percentage",30));
        discounts.add(new Discount(3,"percentage",10));

        Cart cart = new Cart();
        int discount = cart.getDiscount(article,discounts);

        assertEquals(300,discount);
    }

}
