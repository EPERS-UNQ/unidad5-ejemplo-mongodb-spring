package ar.edu.unq.epers.unidad5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Unidad5EjemploMongodbSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Unidad5EjemploMongodbSpringApplication.class, args);
    }

}