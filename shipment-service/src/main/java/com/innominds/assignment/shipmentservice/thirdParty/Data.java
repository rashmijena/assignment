package com.innominds.assignment.shipmentservice.thirdParty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.innominds.assignment.shipmentservice.model.Tracking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
    @JsonProperty("tracking")
    private Tracking tracking;

    public Data(){

    }
    @Override
    public String toString() {
        return tracking.toString();
    }

}
