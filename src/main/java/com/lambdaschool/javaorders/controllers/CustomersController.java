package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.model.Customers;
import com.lambdaschool.javaorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CustomersController
{
    @Autowired
    CustomersService customersService;

    @GetMapping(value = "/customer/order", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers()
    {
        List<Customers> myCustomers = customersService.findAll();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    @GetMapping(value = "/customer/name/{custname}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerByName(@PathVariable String custname)
    {
       Customers c = customersService.findCustomerByName(custname);
       return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PostMapping(value = "/data/customer/new", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@RequestBody Customers newCustomers)
    {
        newCustomers = customersService.save(newCustomers);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/customer/name/{CUSTCODE}").buildAndExpand(newCustomers.getCustcode()).toUri();
        responseHeaders.setLocation(newCustomerURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/data/customer/update/{CUSTCODE}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateCustomer, @PathVariable long CUSTCODE)
    {
       customersService.update(updateCustomer, CUSTCODE);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/data/customer/delete/{CUSTCODE}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long CUSTCODE)
    {
        customersService.delete(CUSTCODE);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
