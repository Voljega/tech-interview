package com.corp.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartDeliveryFeeTest {

    @Test
    public void ShouldHandleFeeCalculationWithMaxNull(){
        List<DeliveryFee> deliveryFeeList = new ArrayList<>();
        DeliveryFee d1 = new DeliveryFee(new EligibleTransactionVolume(0,2),100);
        DeliveryFee d2 = new DeliveryFee(new EligibleTransactionVolume(2,4),50);
        DeliveryFee d3 = new DeliveryFee(new EligibleTransactionVolume(4,null),25);
        deliveryFeeList.add(d1);
        deliveryFeeList.add(d2);
        deliveryFeeList.add(d3);

        Cart cart = new Cart();

        int fee = cart.computeTransportFee(deliveryFeeList,5);
        assertEquals(25,fee);
    }

    @Test
    public void ShouldHandleFeeCalculationWithMinNull(){
        List<DeliveryFee> deliveryFeeList = new ArrayList<>();
        DeliveryFee d1 = new DeliveryFee(new EligibleTransactionVolume(null,2),100);
        DeliveryFee d2 = new DeliveryFee(new EligibleTransactionVolume(2,4),50);
        DeliveryFee d3 = new DeliveryFee(new EligibleTransactionVolume(4,10),25);
        deliveryFeeList.add(d1);
        deliveryFeeList.add(d2);
        deliveryFeeList.add(d3);

        Cart cart = new Cart();

        int fee = cart.computeTransportFee(deliveryFeeList,1);
        assertEquals(100,fee);
    }

    @Test
    public void ShouldHandleFeeCalculationWithChargeTotalGreaterThanMaxMax(){
        List<DeliveryFee> deliveryFeeList = new ArrayList<>();
        DeliveryFee d1 = new DeliveryFee(new EligibleTransactionVolume(null,2),100);
        DeliveryFee d2 = new DeliveryFee(new EligibleTransactionVolume(2,4),50);
        DeliveryFee d3 = new DeliveryFee(new EligibleTransactionVolume(4,10),25);
        deliveryFeeList.add(d1);
        deliveryFeeList.add(d2);
        deliveryFeeList.add(d3);

        Cart cart = new Cart();

        int fee = cart.computeTransportFee(deliveryFeeList,100);
        assertEquals(25,fee);
    }

    @Test
    public void ShouldHandleFeeCalculationWithChargeTotalEqualToOneMinAndOneMax(){
        List<DeliveryFee> deliveryFeeList = new ArrayList<>();
        DeliveryFee d2 = new DeliveryFee(new EligibleTransactionVolume(2,4),50);
        DeliveryFee d3 = new DeliveryFee(new EligibleTransactionVolume(4,10),25);
        deliveryFeeList.add(d2);
        deliveryFeeList.add(d3);

        Cart cart = new Cart();

        int fee = cart.computeTransportFee(deliveryFeeList,4);
        assertEquals(25,fee);
    }
}
