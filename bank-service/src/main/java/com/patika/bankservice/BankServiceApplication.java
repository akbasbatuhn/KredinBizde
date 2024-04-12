package com.patika.bankservice;

import com.patika.bankservice.dto.request.BankRequestDTO;
import com.patika.bankservice.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class BankServiceApplication {
	private final BankService bankService;

	public BankServiceApplication(BankService bankService) {
		this.bankService = bankService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner myCommandLineRunner() {
		return args -> {
			bankService.createBank(new BankRequestDTO("Garanti"));
			bankService.createBank(new BankRequestDTO("Akbank"));
		};
	}

}
