package com.ab.fraud.Service;

import com.ab.fraud.Model.FraudCheckHistory;
import com.ab.fraud.Repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckHistoryService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(Integer customerId){

        //Store check taking place.

        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .isMarkedForFraud(false)
                        .customerId(customerId)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
