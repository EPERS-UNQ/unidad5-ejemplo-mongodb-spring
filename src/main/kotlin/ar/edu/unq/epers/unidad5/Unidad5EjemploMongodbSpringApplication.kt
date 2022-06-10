package ar.edu.unq.epers.unidad5

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class Unidad5EjemploMongodbSpringApplication

fun main(args: Array<String>) {
    runApplication<Unidad5EjemploMongodbSpringApplication>(*args)
}
