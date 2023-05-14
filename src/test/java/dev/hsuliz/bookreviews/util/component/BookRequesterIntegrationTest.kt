package dev.hsuliz.bookreviews.util.component

import dev.hsuliz.bookreviews.model.Book
import dev.hsuliz.bookreviews.util.mapper.BookMapper
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
        mockWebServer.start(8080)

        bookRequester = BookRequester(
            bookMapper,
            WebClient
                .builder()
                .baseUrl("http://${mockWebServer.hostName}:8080")
                .build()
        )
    }

    @AfterEach
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should throw when status 404`() {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
        )

        StepVerifier
            .create(bookRequester.findById(anyString()))
            .verifyError()
    }
}
