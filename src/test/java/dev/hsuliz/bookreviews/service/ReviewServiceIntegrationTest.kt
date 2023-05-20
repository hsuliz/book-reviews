package dev.hsuliz.bookreviews.service

import dev.hsuliz.bookreviews.model.Review
import dev.hsuliz.bookreviews.repository.ReviewRepository
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


@SpringBootTest
@Testcontainers
class ReviewServiceIntegrationTest {
    @Autowired
    private lateinit var reviewService: ReviewService

    @Autowired
    private lateinit var bookService: ReviewService

    @Autowired
    private lateinit var reviewRepository: ReviewRepository

    companion object {
        @JvmStatic
        @Container
        @ServiceConnection
        val mongodb = MongoDBContainer("mongo:6")
    }

    @Test
    fun `save book and find by id`() {

    }
}