package com.customers.backend.app.controllers;

import com.customers.backend.domain.contracts.services.LoggerService;
import com.customers.backend.domain.entities.response.ErrorResponse;
import com.customers.backend.domain.entities.response.CustomersGetAllResponse;
import com.customers.backend.domain.models.CustomerModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    CustomerModel model;

    @Autowired
    LoggerService logger;

    @ApiOperation(value = "get all customers", nickname = "getAllCustomers")
    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CustomersGetAllResponse.class),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal error", response = ErrorResponse.class)
    })
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "0") int start,
                                 @RequestParam(required = false, defaultValue = "10") int limit) {

        try {
            logger.Info("Teste");
            CustomersGetAllResponse dataValue = model.get(start, limit);

            if (dataValue != null
                    && dataValue.getResults() != null
                    && dataValue.getResults().size() > 0)
                return new ResponseEntity(dataValue, HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);

        } catch (Exception ex) {
            return new ResponseEntity(ErrorResponse.builder().message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
