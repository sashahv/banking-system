package com.olekhv.identityservice.service;

// Authentication service

import com.olekhv.identityservice.dto.CustomerLoginRequest;
import com.olekhv.identityservice.dto.CustomerResponse;
import com.olekhv.identityservice.dto.CustomerSaveRequest;
import com.olekhv.identityservice.jwt.JwtTokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final CustomerService customerService;

    public CustomerResponse register(CustomerSaveRequest customerSaveRequest){
        return customerService.saveCustomer(customerSaveRequest);
    }

    public String login(CustomerLoginRequest customerLoginRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                customerLoginRequest.getIdentityNumber().toString(),
                customerLoginRequest.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = "Bearer ";

        return bearer + token;
    }
}
