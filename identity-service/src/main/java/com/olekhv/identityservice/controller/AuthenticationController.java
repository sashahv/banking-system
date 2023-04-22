package com.olekhv.identityservice.controller;

import com.olekhv.identityservice.dto.CustomerLoginRequest;
import com.olekhv.identityservice.dto.CustomerResponse;
import com.olekhv.identityservice.dto.CustomerSaveRequest;
import com.olekhv.identityservice.dto.RestResponse;
import com.olekhv.identityservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal UserDetails userDetails){
        return "hello, " + userDetails.getUsername();
    }

    @PostMapping("/register")
    public ResponseEntity<RestResponse<CustomerResponse>> register(@RequestBody CustomerSaveRequest customerSaveRequest){
        log.info("here");
        CustomerResponse customerResponse = authenticationService.register(customerSaveRequest);

        return ResponseEntity.ok(RestResponse.of(customerResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponse<String>> login(@RequestBody CustomerLoginRequest customerLoginRequest){
        String token = authenticationService.login(customerLoginRequest);

        return ResponseEntity.ok(RestResponse.of(token));
    }
}
