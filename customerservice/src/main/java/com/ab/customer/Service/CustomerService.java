package com.ab.customer.Service;

import com.ab.customer.FraudCheckResponse;
import com.ab.customer.Model.Customer;
import com.ab.customer.Repository.CustomerRepository;
import com.ab.customer.Request.CustomerRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();


        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()

        );

        assert fraudCheckResponse != null;
        if(fraudCheckResponse.isFraudulent()){
            throw new IllegalStateException("Fraud detected");
        }

    }
}
