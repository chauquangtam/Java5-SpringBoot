package edu.poly.fpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import edu.poly.fpt.reponsitories.UserReponsitory;


//@EnableJpaRepositories(basePackageClasses = UserReponsitory.class)
@SpringBootApplication
public class FptshopAssignmentjava5Application {

	public static void main(String[] args) {
		SpringApplication.run(FptshopAssignmentjava5Application.class, args);
	}

}
