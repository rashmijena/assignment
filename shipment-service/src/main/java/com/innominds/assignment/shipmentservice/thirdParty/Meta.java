package com.innominds.assignment.shipmentservice.thirdParty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meta {
    private String code;

    public Meta(){

    }
    @Override
    public String toString() {
        return "Meta{" +
                "code='" + code + '\'' +
                '}';
    }
}
