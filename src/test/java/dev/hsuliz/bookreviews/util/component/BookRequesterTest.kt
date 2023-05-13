package dev.hsuliz.bookreviews.util.component

import dev.hsuliz.bookreviews.util.mapper.BookMapper
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.web.reactive.function.client.WebClient

public class BookRequesterTest {
    @InjectMocks
    lateinit var bookRequester: BookRequester

    @Mock
    lateinit var bookMapper: BookMapper

    @Mock
    lateinit var webClient: WebClient

    @Test
    fun `sas`() {

    }

}