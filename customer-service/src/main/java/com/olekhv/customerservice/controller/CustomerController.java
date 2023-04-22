package com.olekhv.customerservice.controller;

import com.olekhv.customerservice.dto.CustomerResponse;
import com.olekhv.customerservice.dto.CustomerSaveRequest;
import com.olekhv.customerservice.entity.Customer;
import com.olekhv.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerSaveRequest customerSaveRequest){
        CustomerResponse customerResponse = customerService.saveCustomer(customerSaveRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/search/identityNumber/{identityNumber}")
    public ResponseEntity<Customer> findCustomerByIdentityNumber(@PathVariable Long identityNumber){
        Customer customerByIdentityNumber = customerService.findCustomerByIdentityNumber(identityNumber);
        return ResponseEntity.ok(customerByIdentityNumber);
    }

    @GetMapping("/search/id/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        Customer customerByIdentityNumber = customerService.findCustomerById(id);
        return ResponseEntity.ok(customerByIdentityNumber);
    }
}
