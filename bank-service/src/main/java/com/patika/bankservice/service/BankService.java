package com.patika.bankservice.service;

import com.patika.bankservice.dto.converter.BankDTOConverter;
import com.patika.bankservice.dto.request.BankRequestDTO;
import com.patika.bankservice.dto.response.BankResponseDTO;
import com.patika.bankservice.entity.Bank;
import com.patika.bankservice.exception.ExceptionMessages;
import com.patika.bankservice.exception.ResourceNotFoundException;
import com.patika.bankservice.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BankService implements IBankService {

    private final BankRepository repository;

    public BankService(BankRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createBank(BankRequestDTO request) {
        Bank bank = findByName(request.getName());  //check bank already exist
        if(bank == null) {
            Bank newBank = BankDTOConverter.toBank(request);
            Bank saved = repository.save(newBank);

            log.info("Bank saved with id: {}", saved.getId());
        }
    }

    @Override
    public BankResponseDTO getBankById(Long id) {
        return BankDTOConverter.toResponseDTO(findById(id));
    }

    public Bank findById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionMessages.BANK_NOT_FOUND)
        );
    }

    public Bank findByName(String name) {
        Optional<Bank> bank = repository.findByName(name);

        return bank.orElse(null);
    }

    @Override
    public List<BankResponseDTO> getAllBanks() {
        return repository.findAll().stream()
                .map(BankDTOConverter::toResponseDTO)
                .toList();
    }

    @Override
    public BankResponseDTO updateBank(Long id, BankRequestDTO requestDTO) {
        Bank found = findById(id);
        found.setName(requestDTO.getName());

        Bank newApplication = repository.save(found);
        return BankDTOConverter.toResponseDTO(newApplication);
    }

    @Override
    public void deleteById(Long id) {
        Bank bank = findById(id);
        repository.delete(bank);

        log.info("Bank deleted with id: {}", id);
    }

    public Bank getBankByName(String bankName) {
        return repository.findByName(bankName).orElseThrow(
                () -> new ResourceNotFoundException(ExceptionMessages.BANK_NOT_FOUND)
        );
    }
}
