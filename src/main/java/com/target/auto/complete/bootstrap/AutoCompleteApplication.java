package com.target.auto.complete.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.target.auto.complete.*")
public class AutoCompleteApplication {

  /*
   * Swagger UI 	- 	http://localhost:8080/swagger-ui.html
   * local host 	- 	http://localhost:8080/v1/products
   */
  public static void main(String[] args) {
    SpringApplication.run(AutoCompleteApplication.class, args);
  }
}
