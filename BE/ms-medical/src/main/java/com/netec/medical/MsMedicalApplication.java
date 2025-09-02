package com.netec.medical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
    servers = {
        @Server(url = "/api", description = "API detrás de Ingress"),
        @Server(url = "http://localhost:8080/api", description = "Local dev"),
        @Server(url = "http://medical.local:30080/api", description = "Ingress / Kubernetes")
    }
)
public class MsMedicalApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsMedicalApplication.class, args);
    }
}
