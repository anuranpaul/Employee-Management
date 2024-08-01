package com.company.employee;

//import org.hsqldb.util.DatabaseManagerSwing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@OpenAPIDefinition(info =  @Info(
			title = "Employee API",
			description = "Handling CRUD Ops on the Employee REST API"
))
public class EmployeeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EmployeeApplication.class);
	}
}
