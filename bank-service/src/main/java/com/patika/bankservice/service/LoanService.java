package com.patika.bankservice.service;

import com.patika.bankservice.dto.converter.LoanDTOConverter;
import com.patika.bankservice.dto.response.GetLoanResponse;
import com.patika.bankservice.dto.response.LoanResponseDTO;
import com.patika.bankservice.entity.Bank;
import com.patika.bankservice.entity.Loan;
import com.patika.bankservice.exception.ExceptionMessages;
import com.patika.bankservice.exception.ResourceNotFoundException;
import com.patika.bankservice.repository.LoanRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoanService implements ILoanService {

    private final LoanRepository repository;

    private final BankService bankService;

    public LoanService(LoanRepository repository, BankService bankService) {
        this.repository = repository;
        this.bankService = bankService;
    }

    @Transactional
    @Override
    public LoanResponseDTO createLoan(GetLoanResponse response, Bank bank) {
        if(bank == null) throw new ResourceNotFoundException(ExceptionMessages.BANK_NOT_FOUND);

        Loan createdLoan = LoanDTOConverter.toLoan(response, bank);

        Loan saved = repository.save(createdLoan);

        evictCache();

        log.info("Loan saved with id: {}", saved.getId());
        return LoanDTOConverter.toResponse(saved);
    }

    @CacheEvict(value = "loans", allEntries = true)
    private void evictCache() {
        // evict cache
    }

    @Cacheable(value = "loans", key = "#id")
    @Override
    public LoanResponseDTO getLoanById(Long id) {
        return LoanDTOConverter.toResponse(findLoanById(id));
    }

    public Loan findLoanById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionMessages.LOAN_NOT_FOUND)
        );
    }

    @Override
    public List<LoanResponseDTO> getAllLoans() {
        return repository.findAll().stream()
                .map(LoanDTOConverter::toResponse)
                .toList();
    }

    @Transactional
    @Override
    public LoanResponseDTO updateLoan(Long id, GetLoanResponse response) {
        Loan loan = findLoanById(id);

        log.info("Loan found with id: {}", id);
        return LoanDTOConverter.toResponse(loan);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Loan loan = findLoanById(id);

        repository.delete(loan);
        log.info("Loan deleted with id: {}", id);
    }

    public boolean isLoanExist(Long idFromBank, Long bankId) {
        Loan loan = repository.findByLoanIdFromBankAndBankId(idFromBank, bankId);

        return loan != null;
    }
}
