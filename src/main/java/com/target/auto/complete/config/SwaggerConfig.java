package com.target.auto.complete.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket productApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.target.auto.complete.rest"))
        .paths(regex("/v1.*"))
        .build()
        .apiInfo(metaInfo());
  }

  private ApiInfo metaInfo() {
    return new ApiInfo(
        "Product Endpoints",
        "Target case study",
        "1.0",
        "@Copy right",
        "Farzain Pathan",
        "farzainpathan@rocketmail.com",
        "www.farzainpathan.com");
  }
}
