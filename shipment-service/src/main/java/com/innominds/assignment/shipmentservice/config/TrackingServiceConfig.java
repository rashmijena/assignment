package com.innominds.assignment.shipmentservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "thirdparty.tracking.service")
public class TrackingServiceConfig {
   // @Value("ThirdParty.Tracking.service.baseURL")
    String baseURL;
   // @Value("ThirdParty.Tracking.service.trackingAPI")
    String trackingAPI;
   // @Value("ThirdParty.Tracking.service.apiKey")
    String apiKey;
}
