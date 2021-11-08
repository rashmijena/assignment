package com.innominds.assignment.shipmentservice.util;

import com.innominds.assignment.shipmentservice.model.Tracking;
import com.innominds.assignment.shipmentservice.thirdParty.APIResponse;
import com.innominds.assignment.shipmentservice.thirdParty.Data;
import com.innominds.assignment.shipmentservice.thirdParty.Meta;

public class TestUtil {

    public static APIResponse buildGetResponse(String trackingId, String slug, String statusCode){
        Meta m = new Meta();
        m.setCode(statusCode);
        Tracking t = new Tracking();
        t.setId("dummYId");
        t.setTrackingNumber(trackingId);
        t.setSlug(slug);
        Data d = new Data();
        d.setTracking(t);
        APIResponse res = new APIResponse();
        res.setMeta(m);
        res.setData(d);
        return res;
    }
}
