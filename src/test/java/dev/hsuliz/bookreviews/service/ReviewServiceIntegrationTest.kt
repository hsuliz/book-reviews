package dev.hsuliz.bookreviews.service

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class ReviewServiceIntegrationTest {


    companion object {
        @Container
        @ServiceConnection
        val neo4j = MongoDBContainer("mongo:6")
    }
}