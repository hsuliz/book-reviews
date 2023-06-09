package dev.hsuliz.bookreviews.component

import dev.hsuliz.bookreviews.mapper.BookMapper
import dev.hsuliz.bookreviews.service.BookRequester
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.StepVerifier

@SpringBootTest
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
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
        )
        StepVerifier
            .create(bookRequester.findById("7777"))
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