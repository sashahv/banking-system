package com.olekhv.identityservice.service;

import com.olekhv.identityservice.dto.CustomerResponse;
import com.olekhv.identityservice.dto.CustomerSaveRequest;
import com.olekhv.identityservice.entity.Customer;
import com.olekhv.identityservice.mapper.CustomerMapper;
import com.olekhv.identityservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse saveCustomer(CustomerSaveRequest customerSaveRequest){
        Customer customer = customerMapper.mapRequestToCustomer(customerSaveRequest);
        customerRepository.save(customer);
        return customerMapper.mapCustomerToResponse(customer);
    }

    public Customer findCustomerByIdentityNumber(Long identityNumber){
        return customerRepository.findByIdentityNumber(identityNumber).orElseThrow(
                () -> new IllegalStateException("User with identity number " + identityNumber + " not found")
        );
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("User with id " + id + " not found")
        );
    }
}
