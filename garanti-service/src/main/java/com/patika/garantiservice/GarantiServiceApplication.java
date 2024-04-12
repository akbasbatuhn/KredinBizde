package com.patika.garantiservice;

import com.patika.garantiservice.dto.request.LoanRequest;
import com.patika.garantiservice.enums.LoanType;
import com.patika.garantiservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GarantiServiceApplication {

    @Autowired
    private LoanService loanService;

    public static void main(String[] args) {
        SpringApplication.run(GarantiServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return args -> {
            loanService.createLoan(new LoanRequest(LoanType.STUDENT_LOAN, 2.98));
            loanService.createLoan(new LoanRequest(LoanType.HOME_LOAN, 5.31));
            loanService.createLoan(new LoanRequest(LoanType.CAR_LOAN, 3.87));
            loanService.createLoan(new LoanRequest(LoanType.LAND_LOAN, 6.90));
        };
    }

}
