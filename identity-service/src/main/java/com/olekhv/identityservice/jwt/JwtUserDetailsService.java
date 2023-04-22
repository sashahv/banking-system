package com.olekhv.identityservice.jwt;

import com.olekhv.identityservice.entity.Customer;
import com.olekhv.identityservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        long identityNumber = Long.parseLong(username);
        log.info("loadUserByUsername");

        Optional<Customer> customer = customerRepository.findByIdentityNumber(identityNumber);

        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("Customer not found");
        }

        return JwtUserDetails.create(customer.get());
    }

    public UserDetails loadUserByUserId(Long id){
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            throw new IllegalStateException("Customer not found");
        }

        return JwtUserDetails.create(customer.get());
    }
}
