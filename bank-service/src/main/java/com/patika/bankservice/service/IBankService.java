package com.patika.bankservice.service;

import com.patika.bankservice.dto.request.BankRequestDTO;
import com.patika.bankservice.dto.response.BankResponseDTO;

import java.util.List;

public interface IBankService {

    void createBank(BankRequestDTO request);
    BankResponseDTO getBankById(Long id);
    List<BankResponseDTO> getAllBanks();
    BankResponseDTO updateBank(Long id, BankRequestDTO requestDTO);
    void deleteById(Long id);
}
