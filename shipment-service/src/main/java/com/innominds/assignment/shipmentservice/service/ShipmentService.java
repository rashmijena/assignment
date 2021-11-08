package com.innominds.assignment.shipmentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innominds.assignment.shipmentservice.config.TrackingServiceConfig;
import com.innominds.assignment.shipmentservice.exception.SomeProcessingException;
import com.innominds.assignment.shipmentservice.response.CustomResponse;
import com.innominds.assignment.shipmentservice.thirdParty.APIResponse;
import com.innominds.assignment.shipmentservice.model.Shipment;
import com.innominds.assignment.shipmentservice.util.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class ShipmentService {

    @Autowired
    private RestTemplate template;
    @Autowired
    private TrackingServiceConfig trackingServiceConfig;
    private HttpHeaders headers;
    private ObjectMapper mapper;

    private static final String baseURL="http://localhost:8080/tracking";

    @PostConstruct
    public void init(){
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("aftership-api-key",trackingServiceConfig.getApiKey());
        mapper = new ObjectMapper();
    }

    private CustomResponse parseMessageAndBuildPostResponse(APIResponse apiResponse ){
        CustomResponse res = new CustomResponse();
        if(apiResponse.getMeta().getCode().startsWith("20")){
                res.setStatusCode(HttpStatus.OK.name());
                res.setMessage(RequestBuilder.buildURL(Arrays.asList(baseURL,apiResponse.getData().getTracking().getSlug(),apiResponse.getData().getTracking().getTrackingNumber())));
          }else {
              res.setStatusCode(apiResponse.getMeta().getCode());
              res.setMessage(apiResponse.getData().toString());
          }
        return res;
    }

    public CustomResponse addShipment(Shipment item) throws SomeProcessingException {
        String path = RequestBuilder.buildURL(Arrays.asList(trackingServiceConfig.getBaseURL(),trackingServiceConfig.getTrackingAPI()));

        try {

            String requestBody = mapper.writeValueAsString(item);
            StringBuilder sb = new StringBuilder("{ \"tracking\": ").append(requestBody).append("}");
            System.out.println("request body:"+sb.toString());
            HttpEntity<String> entity = new HttpEntity<String>(sb.toString(),headers);
            ResponseEntity<APIResponse> res = template.postForEntity(path,entity,APIResponse.class);
            return parseMessageAndBuildPostResponse(res.getBody());
        } catch (JsonProcessingException e) {
            throw new SomeProcessingException(e.getMessage());
        } catch (RestClientException e) {
            throw new SomeProcessingException(e.getMessage());
        }
    }

    //TODO need to handle exceptions like timedout etc
    // which can be done by retry
    public APIResponse getShipment(String slug, String trackingNum){
        String path = RequestBuilder.buildURL(Arrays.asList(trackingServiceConfig.getBaseURL(),trackingServiceConfig.getTrackingAPI(),slug,trackingNum));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        APIResponse res = template.exchange(path, HttpMethod.GET,entity, APIResponse.class).getBody();
        return  res;
    }
}
