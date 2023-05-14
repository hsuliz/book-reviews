package dev.hsuliz.bookreviews.util.component

import com.fasterxml.jackson.databind.ObjectMapper
import dev.hsuliz.bookreviews.model.Book
import dev.hsuliz.bookreviews.util.dto.BookResponse
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
    fun `should return book when status 200`() {
        mockWebServer.enqueue(
            MockResponse()
                .addHeader("Content-Type", "application/json")
                .setResponseCode(200)
                .setBody(
                    ObjectMapper().writeValueAsString(
                        BookResponse(
                            "9463666656",
                            "Kees Vuik, Fred Vermolen, Martin van Gijzen",
                            "Numerical Methods for Ordinary Differential Equations",
                            "2023",
                            "https://www.dbooks.org/img/books/9463666656s.jpg"
                        )
                    )
                )
        )


        StepVerifier
            .create(bookRequester.findById(anyString()))
            .expectNext(Book(
                "9463666656",
                "Kees Vuik, Fred Vermolen, Martin van Gijzen",
                "Numerical Methods for Ordinary Differential Equations",
                "2023",
                "https://www.dbooks.org/img/books/9463666656s.jpg"
            ))
            .expectNextCount(1)
            .verifyComplete()
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
Book(id=null, externalId=9463666656, title=Kees Vuik, Fred Vermolen, Martin van Gijzen, author=Numerical Methods for Ordinary Differential Equations, year=2023
Book(id=null, externalId=9463666656, title=Numerical Methods for Ordinary Differential Equations, author=Kees Vuik, Fred Vermolen, Martin van Gijzen, year=2023,