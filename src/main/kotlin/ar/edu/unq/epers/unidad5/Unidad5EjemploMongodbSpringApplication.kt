package ar.edu.unq.epers.unidad5

import ar.edu.unq.epers.unidad5.dao.ProductoDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class Unidad5EjemploMongodbSpringApplication

fun main(args: Array<String>) {
    runApplication<Unidad5EjemploMongodbSpringApplication>(*args)
}
