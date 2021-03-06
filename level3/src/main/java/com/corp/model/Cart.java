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

    public int computeTransportFee(List<DeliveryFee> deliveryFees, int chargedTotal){
        int maxedFeePrice = 0;
        for (DeliveryFee deliFee : deliveryFees){
            EligibleTransactionVolume eligTransactVol = deliFee.getEligibleTransactionVolume();
            //handles null cases for min et max
            int min = (eligTransactVol.getMin()!=null)?eligTransactVol.getMin():Integer.MIN_VALUE;
            int max = (eligTransactVol.getMax()!=null)?eligTransactVol.getMax():Integer.MAX_VALUE;

            if (chargedTotal >= min && chargedTotal < max) return deliFee.getPrice();
            // remember price last maxed out by chargedTotal
            else if (chargedTotal >= max) maxedFeePrice = deliFee.getPrice();
        }

        //Handle case where chargedTotal is more than the max of the max in the DeliveryFee list
        if (maxedFeePrice!=0) return maxedFeePrice;
        else return 0;
    }

    public int getDiscount(Article article,List<Discount> discounts){
        for (Discount discount : discounts){
            // There could be several discounts on same article nut for simplicity sake
            // let's say there is only one
            if (discount.getArticleId() == article.getId()){
                if (discount.getType().equals("amount"))
                    return discount.getValue();
                else if (discount.getType().equals("percentage")) {
                    // in percentage case we want the discount to be rounded up if it has decimals
                    double percentageDiscount = (double) article.getPrice() * (double)discount.getValue() / 100;
                    return (int)((percentageDiscount % 1 == 0 )? percentageDiscount : percentageDiscount + 1);
                }
                else //unknown type
                    return 0;
            }
        }
        return 0;
    }

    public void computeCart( DataStore dataStore){
        int total = 0;
        for (Item item : this.getItems()){
            Article article = dataStore.getData().getArticlesById(item.getArticleId());
            int price = article!=null?article.getPrice():0;
            //price are in cents so let's use int for discount too
            int discount = getDiscount(article,dataStore.getData().getDiscounts());
            total += (price - discount) * item.getQuantity();
        }

        total += computeTransportFee(dataStore.getData().getDeliveryFees(),total);//should be handled by cart

        setTotal(total);
    }
}
