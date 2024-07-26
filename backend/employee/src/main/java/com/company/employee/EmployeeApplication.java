package com.company.employee;

//import org.hsqldb.util.DatabaseManagerSwing;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =  @Info(
			title = "Employee API",
			description = "Handling CRUD Ops on the Employee REST API"
))
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

//	@PostConstruct
//	public void getDbManager(){
//		org.hsqldb.util.DatabaseManagerSwing.main(
//				new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
//	}

}
