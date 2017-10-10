package com.corp.store;

import com.corp.model.Article;
import com.corp.model.Cart;
import com.corp.model.Data;
import com.corp.model.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DataStoreTest {

    @Test
    public void SampleDataStoreShouldLoadWell(){
        DataStore dataStore = new DataStore();
        Data data = dataStore.loadObjects();

        assertEquals(4,data.getArticles().size());
        assertNotNull(data.getArticlesById(1));
        assertNotNull(data.getArticlesById(2));
        assertNotNull(data.getArticlesById(3));
        assertNotNull(data.getArticlesById(4));
        assertEquals(null,data.getArticlesById(5));
        assertEquals(3,data.getCarts().size());
        assertEquals(3,data.getCarts().get(0).getItems().size());
        assertEquals(2,data.getCarts().get(1).getItems().size());
        assertEquals(0,data.getCarts().get(2).getItems().size());
    }

    @Test
    public void DataStoreShouldComputeCart(){
        DataStore dataStore = new DataStore();
        dataStore.getData().getArticles().add(new Article(1, "article1", 10));
        dataStore.getData().getArticles().add(new Article(2, "article2", 5));
        dataStore.getData().getArticles().add(new Article(3, "article3", 1));
        dataStore.getData().sortArticles();//not very nice

        Cart cart1 = new Cart();
        dataStore.getData().getCarts().add(cart1);
        cart1.getItems().add(new Item(1, 5));
        Cart cart2 = new Cart();
        dataStore.getData().getCarts().add(cart2);
        cart2.getItems().add(new Item(2, 2));
        Cart cart3 = new Cart();
        dataStore.getData().getCarts().add(cart3);
        dataStore.computeCarts();

        assertEquals(50,dataStore.getData().getCarts().get(0).getTotal());
        assertEquals(10,dataStore.getData().getCarts().get(1).getTotal());
        assertEquals(0,dataStore.getData().getCarts().get(2).getTotal());
    }

    @Test
    public void DataStoreShouldWriteOutputCorrectly(){
        // would not test much than Jasckson's ability to serialize Json correctly
    }
}
