package dev.hsuliz.bookreviews.service

import dev.hsuliz.bookreviews.dto.ReviewRequest
import dev.hsuliz.bookreviews.model.Book
import dev.hsuliz.bookreviews.model.Review
import dev.hsuliz.bookreviews.repository.TestBookRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.core.publisher.Mono


@SpringBootTest
@Testcontainers
@AutoConfigureWebTestClient
class ServiceIntegrationTest {
    @Autowired
    @Qualifier("testBookRepository")
    private lateinit var testBookRepository: TestBookRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    companion object {
        @JvmStatic
        @Container
        @ServiceConnection
        val mongodb = MongoDBContainer("mongo:6")
    }

    @Test
    fun `when save book, add review, find by id expect return saved book with new review`() {
        var givenBook: Book? = null
        val givenExternalBookId = "9463666656"
        var givenReview: ReviewRequest? = null

        webTestClient
            .post()
            .uri("/book")
            .bodyValue(givenExternalBookId)
            .exchange()
            .expectStatus().isCreated

        testBookRepository
            .findBookByExternalId(Mono.just(givenExternalBookId))
            .map {
                givenBook = it
                givenReview = ReviewRequest(Review(5, "Good book"), it.id)
            }
            .block()

        webTestClient
            .post()
            .uri("/review")
            .bodyValue(givenReview!!)
            .exchange()
            .expectStatus().isCreated

        webTestClient
            .get()
            .uri("/book/${givenBook!!.id}")
            .exchange()
            .expectStatus().is2xxSuccessful

    }
}