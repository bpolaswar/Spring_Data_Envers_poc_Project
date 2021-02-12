package com.auditpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@ComponentScan(basePackages = {"com.auditpoc"})
@EntityScan(basePackages = {"com.auditpoc.entity"})
public class SpringBootAuditingApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAuditingApplication.class, args);
	}
}
