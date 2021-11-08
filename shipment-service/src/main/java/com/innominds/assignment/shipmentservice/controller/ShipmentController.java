package com.innominds.assignment.shipmentservice.controller;

import com.innominds.assignment.shipmentservice.exception.SomeProcessingException;
import com.innominds.assignment.shipmentservice.response.CustomResponse;
import com.innominds.assignment.shipmentservice.thirdParty.APIResponse;
import com.innominds.assignment.shipmentservice.model.Shipment;
import com.innominds.assignment.shipmentservice.response.CustomExceptionResponse;
import com.innominds.assignment.shipmentservice.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ShipmentController {

    @Autowired
    private ShipmentService service;


    @GetMapping(path="/tracking/{slug}/{trackNum}",produces = MediaType.APPLICATION_JSON_VALUE)
    public APIResponse getShipment(@PathVariable("slug") String slug, @PathVariable("trackNum") String trackNum){
       return service.getShipment(slug,trackNum);

    }


    @PostMapping(path="/tracking", consumes = MediaType.APPLICATION_JSON_VALUE,produces =  MediaType.APPLICATION_JSON_VALUE)
    public CustomResponse createShipment(@RequestBody @Valid Shipment shipment) {
        return service.addShipment(shipment);
    }

    /*
        This is just to demonstrate the exception handle for this controller
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected CustomExceptionResponse handleValidationException(MethodArgumentNotValidException objException)
    {
        CustomExceptionResponse response = new CustomExceptionResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST.name());
        response.setMessage("Required value:"+objException.getFieldError().getField()+" and error is:"+objException.getFieldError().getDefaultMessage());
        return response;
    }

    @ExceptionHandler(SomeProcessingException.class)
    protected CustomExceptionResponse handleProcessingException(SomeProcessingException objException)
    {
        CustomExceptionResponse response = new CustomExceptionResponse();
        response.setStatusCode(HttpStatus.EXPECTATION_FAILED.name());
        response.setMessage("Something went wrong");
        return response;
    }
}
