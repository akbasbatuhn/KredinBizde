package com.patika.bankservice.dto.converter;

import com.patika.bankservice.dto.request.BankRequestDTO;
import com.patika.bankservice.dto.response.BankResponseDTO;
import com.patika.bankservice.entity.Bank;

public class BankDTOConverter {

    private BankDTOConverter() {}

    public static BankResponseDTO toResponseDTO(Bank bank) {
        return BankResponseDTO.builder()
                .id(bank.getId())
                .name(bank.getName())
                .loanList(bank.getLoanList())
                .build();
    }

    public static Bank toBank(BankRequestDTO request) {
        return Bank.builder()
                .name(request.getName())
                .build();
    }
}
