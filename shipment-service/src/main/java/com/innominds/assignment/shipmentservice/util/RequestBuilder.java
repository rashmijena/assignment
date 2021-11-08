package com.innominds.assignment.shipmentservice.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class RequestBuilder {

    public static String buildURL(List<String> paths) {
        return StringUtils.join(paths,"/");
    }

    public static void main(String[] args) {
                System.out.println(RequestBuilder.buildURL( Arrays.asList("http://hello.com:8080","tracking","usps","98610")));
    }
}
