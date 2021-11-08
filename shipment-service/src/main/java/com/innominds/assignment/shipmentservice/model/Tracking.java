package com.innominds.assignment.shipmentservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tracking {
    @JsonProperty("id")
    private String id;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("tracking_number")
    private String trackingNumber;
    @JsonProperty("tag")
    private String status;
    @JsonProperty("tracking_origin_country")
    private String originCountry;
    @JsonProperty("tracking_destination_country")
    private String destCountry;


    @Override
    public String toString() {
        return "Tracking{" +
                "id='" + id + '\'' +
                ", slug='" + slug + '\'' +
                ", trackingNumber='" + trackingNumber + '\'' +
                '}';
    }
}
