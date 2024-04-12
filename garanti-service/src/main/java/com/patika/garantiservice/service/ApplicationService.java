package com.patika.garantiservice.service;

import com.patika.garantiservice.converter.ApplicationConverter;
import com.patika.garantiservice.dto.request.ApplicationRequest;
import com.patika.garantiservice.dto.response.ApplicationResponse;
import com.patika.garantiservice.entity.Application;
import com.patika.garantiservice.entity.Loan;
import com.patika.garantiservice.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ApplicationService {

    private final ApplicationRepository repository;

    private final ApplicationConverter applicationConverter;

    private final LoanService loanService;

    public ApplicationService(ApplicationRepository repository, ApplicationConverter applicationConverter, LoanService loanService) {
        this.repository = repository;
        this.applicationConverter = applicationConverter;
        this.loanService = loanService;
    }

    @Transactional
    public ApplicationResponse createApplication(ApplicationRequest request) {
        Loan loan = getLoan(request.getLoanId());
        Application created = applicationConverter.toApplication(request);
        created.setLoan(loan);
        created.calculateRepaymentAmount();

        Application application = repository.save(created);

        return applicationConverter.toResponse(application);
    }

    public List<ApplicationResponse> getAll() {
        return applicationConverter.toResponseList(repository.findAll());
    }

    public ApplicationResponse getApplicationById(Long id) {
        Application application = findApplicationById(id);

        log.info("Application found with id: {}", id);
        return applicationConverter.toResponse(application);
    }

    private Application findApplicationById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found with id " + id)
        );
    }

    @Transactional
    public ApplicationResponse updateApplication(Long id, ApplicationRequest request) {
        Loan loan = getLoan(request.getLoanId());

        Application found = findApplicationById(id);

        found.setAmount(request.getAmount());
        found.setInstallment(request.getInstallment());
        found.setLoan(loan);
        found.calculateRepaymentAmount();

        Application newApplication = repository.save(found);

        return applicationConverter.toResponse(newApplication);
    }

    @Transactional
    public void deleteApplicationById(Long id) {
        Application application = findApplicationById(id);

        repository.delete(application);
        log.info("Application deleted with id: {}", id);
    }

    private Loan getLoan(Long id) {
        Loan loan = loanService.findLoanById(id);
        if(loan == null) throw new RuntimeException("Loan not found");
        return loan;
    }
}
