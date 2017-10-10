package com.corp.store;

import com.corp.model.Cart;
import com.corp.model.Data;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * DataStore loads and store data
 */
public class DataStore {

    private Data data = new Data();

    public Data loadObjects() {
        Data data = new Data();
        try {
            URL jsonFile = getClass().getResource("/data.json");
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readValue(new File(jsonFile.getFile()), new TypeReference<Data>() {});

        }
        catch(IOException e){
           System.out.println(e);
        }

        // Convert articles list to map for better handling
        // should be custom handled in the mapper to avoid a second full article list traversal
        data.sortArticles();

        this.data = data;
        return data;
    }

    public void writeObjects(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
            objectMapper.writeValue(new File("./output.json"), data);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public Data getData() {
        return data;
    }

    public void computeCarts(){
        for(Cart c : data.getCarts()){
            c.computeCart(this);
        }
    }

    public static void main(String[] args){
        DataStore dataStore = new DataStore();
        dataStore.loadObjects();
        dataStore.computeCarts();
        dataStore.writeObjects();
    }
}
