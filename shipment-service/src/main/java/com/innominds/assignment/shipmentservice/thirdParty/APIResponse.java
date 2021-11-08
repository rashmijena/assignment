package com.innominds.assignment.shipmentservice.thirdParty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.innominds.assignment.shipmentservice.model.Tracking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse {
    private Meta meta;
    @JsonProperty("data")
    private Data data;

    @Override
    public String toString() {
        return "Response{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
