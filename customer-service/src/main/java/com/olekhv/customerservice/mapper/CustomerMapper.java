package com.olekhv.customerservice.mapper;

import com.olekhv.customerservice.dto.CustomerResponse;
import com.olekhv.customerservice.dto.CustomerSaveRequest;
import com.olekhv.customerservice.entity.Customer;
import com.olekhv.customerservice.repository.CustomerRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Random;

public class CustomerMapper {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer mapRequestToCustomer(CustomerSaveRequest customerSaveRequest) {
        return Customer.builder()
                .createdAt(LocalDate.now())
                .identityNumber(generateIdentityNumber())
                .password(passwordEncoder.encode(customerSaveRequest.getPassword()))
                .name(customerSaveRequest.getName())
                .surname(customerSaveRequest.getSurname())
                .build();
    }

    public CustomerResponse mapCustomerToResponse(Customer customer) {
        return CustomerResponse
                .builder()
                .identityNumber(customer.getIdentityNumber())
                .name(customer.getName())
                .surname(customer.getSurname())
                .build();
    }

    public Long generateIdentityNumber(){
        int randomNumber = 100000000 + new Random().nextInt(900000000);
        boolean isUniqueIdentityNumber = checkIfIdentityNumberIsUnique((long) randomNumber);
        if(!isUniqueIdentityNumber) generateIdentityNumber();
        return (long) randomNumber;
    }

    public boolean checkIfIdentityNumberIsUnique(Long identityNumber){
        return customerRepository.findByIdentityNumber(identityNumber).isEmpty();
    }
}
