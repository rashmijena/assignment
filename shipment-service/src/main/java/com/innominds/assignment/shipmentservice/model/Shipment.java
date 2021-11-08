package com.innominds.assignment.shipmentservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@AllArgsConstructor
public class Shipment {

    @NotEmpty(message = "origin must not be empty/blank")
    private String origin;
    @NotEmpty(message = "destination must not be empty/blank")
    private String destination;
    @NotBlank(message = "trackingNumber must not be empty/blank")
    private String trackingNumber;
    private String courierCode;
    @JsonProperty(value = "tracking_number")
    public String getTrackingNumber() {
        return trackingNumber;
    }

    @JsonProperty(value = "trackingNumber")
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    @JsonProperty(value = "slug")
    public String getCourierCode() {
        return courierCode;
    }

    @JsonProperty(value = "courierCode")
    public void setCourierCode(String courierCode) {
        this.courierCode = courierCode;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", courierCode=" + courierCode +
                '}';
    }
}
