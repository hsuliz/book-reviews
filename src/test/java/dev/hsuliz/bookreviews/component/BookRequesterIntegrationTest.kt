package dev.hsuliz.bookreviews.component

import com.fasterxml.jackson.databind.ObjectMapper
import dev.hsuliz.bookreviews.dto.BookRequesterRequest
import dev.hsuliz.bookreviews.mapper.BookMapper
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.StepVerifier

@SpringBootTest
@ContextConfiguration(classes = [BookMapper::class])
class BookRequesterIntegrationTest {
    @Autowired
    private lateinit var bookMapper: BookMapper

    private lateinit var bookRequester: BookRequester

    private lateinit var mockWebServer: MockWebServer

    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        bookRequester = BookRequester(
            bookMapper,
            WebClient
                .builder()
                .baseUrl("http://${mockWebServer.hostName}:${mockWebServer.port}")
                .build()
        )
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should return mapped book when status 200`() {
        val book = BookRequesterRequest(
            "9463666656",
            "Kees Vuik, Fred Vermolen, Martin van Gijzen",
            "Numerical Methods for Ordinary Differential Equations",
            "2023",
            "https://www.dbooks.org/img/books/9463666656s.jpg"
        )
        mockWebServer.enqueue(
            MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody(ObjectMapper().writeValueAsString(book))
        )
        StepVerifier
            .create(bookRequester.findById(book.id))
            .expectNext(bookMapper.responseToModel(book))
            .verifyComplete()
    }

    @Test
    fun `should throw when status 404`() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
        )
        StepVerifier
            .create(bookRequester.findById("777"))
            .verifyError()
    }
}