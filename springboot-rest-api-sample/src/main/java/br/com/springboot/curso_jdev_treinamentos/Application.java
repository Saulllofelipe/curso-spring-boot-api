package br.com.springboot.curso_jdev_treinamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * Spring Boot application starter class
 */
@SpringBootApplication //essa anotation iniciar a aplicação web. Lê, configura e sobe todo o projeto no ar
public class Application {
    public static void main(String[] args) {
		SpringApplication.run(Application.class, args); /* É a linha principal que roda o projeto Java Spring Boot*/
    }
}
