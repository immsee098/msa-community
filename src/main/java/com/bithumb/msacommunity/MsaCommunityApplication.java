package com.bithumb.msacommunity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@OpenAPIDefinition(info = @Info(title = "Community Swagger", version = "1.0", description = "Documentation APIs v1.0"))
@SpringBootApplication
//@EnableDiscoveryClient
public class MsaCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaCommunityApplication.class, args);
    }

}
