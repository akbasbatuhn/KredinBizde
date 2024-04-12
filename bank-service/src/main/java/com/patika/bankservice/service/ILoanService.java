package com.patika.bankservice.service;

import com.patika.bankservice.dto.response.GetLoanResponse;
import com.patika.bankservice.dto.response.LoanResponseDTO;
import com.patika.bankservice.entity.Bank;

import java.util.List;

public interface ILoanService {

    LoanResponseDTO createLoan(GetLoanResponse response, Bank bank);
    LoanResponseDTO getLoanById(Long id);
    List<LoanResponseDTO> getAllLoans();
    LoanResponseDTO updateLoan(Long id, GetLoanResponse requestDTO);
    void deleteById(Long id);
}
