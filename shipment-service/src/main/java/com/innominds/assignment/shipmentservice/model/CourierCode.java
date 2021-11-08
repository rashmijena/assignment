package com.innominds.assignment.shipmentservice.model;

public enum CourierCode {
    FEDEX("FedEx"),
    UPS("UPS"),
    USPS("USPS");

    private final String  val;
    CourierCode(String val){
        this.val=val;
    }

    public String getEnumValue(){
        return val;
    }

}
