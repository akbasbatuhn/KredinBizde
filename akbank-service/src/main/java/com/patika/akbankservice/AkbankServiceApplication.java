package com.patika.akbankservice;

import com.patika.akbankservice.dto.request.LoanRequest;
import com.patika.akbankservice.enums.LoanType;
import com.patika.akbankservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class AkbankServiceApplication {

    @Autowired
    private LoanService loanService;


    public static void main(String[] args) {
        SpringApplication.run(AkbankServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner myCommandLineRunner() {
        return args -> {
            loanService.createLoan(new LoanRequest(LoanType.STUDENT_LOAN, 3.67));
            loanService.createLoan(new LoanRequest(LoanType.HOME_LOAN, 5.98));
            loanService.createLoan(new LoanRequest(LoanType.CAR_LOAN, 4.77));
            loanService.createLoan(new LoanRequest(LoanType.LAND_LOAN, 5.66));
        };
    }

}
