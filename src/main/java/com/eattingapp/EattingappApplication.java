package com.eattingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import static com.eattingapp.database.StartUseCases.initFillDatabase;


@SpringBootApplication
public class EattingappApplication {

	public static void main(String[] args) throws SQLException{
		initFillDatabase(args);
		SpringApplication.run(EattingappApplication.class, args);
	}

}
