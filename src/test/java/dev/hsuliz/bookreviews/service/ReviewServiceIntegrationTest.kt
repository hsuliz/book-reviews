package dev.hsuliz.bookreviews.service

import dev.hsuliz.bookreviews.repository.ReviewRepository
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
    fun `save book, add review, find by id`() {

    }
}