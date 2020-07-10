package com.personal.miaplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = "com.personal")
@EnableAsync
@EnableJpaRepositories("com.personal.persistence.repo")
@EntityScan("com.personal.persistence.model")

public class MiAplicacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiAplicacionApplication.class, args);
	}

}
