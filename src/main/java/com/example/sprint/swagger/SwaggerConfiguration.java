package com.example.sprint.swagger;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {
    private final String version = "1.0";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sprint API")
                .description("hi :)")
                .version(version)
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.sprint.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
