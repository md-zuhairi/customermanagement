package com.zadeon.customermanagement;

import com.zadeon.customermanagement.entity.Customer;
import com.zadeon.customermanagement.respository.customerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SpringBootApplication
public class CustomermanagementApplication implements CommandLineRunner {

	@Autowired
	private customerRepository repo;
	SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	Date date;

	{
		try {
			date = myFormat.parse("07/12/2022");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	Customer customer = new Customer(15758, "TGW Construction Pte Ltd", "Singapore", 98473823, "DELL Opt 5050 SFF, Samsung 24R 350F2F LED", 650, date);

	public static void main(String[] args) {
		SpringApplication.run(CustomermanagementApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		repo.save(customer);
	}
}
