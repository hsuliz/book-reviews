package dev.hsuliz.bookreviews.service

import dev.hsuliz.bookreviews.model.Book
import dev.hsuliz.bookreviews.repository.BookRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

public class BookServiceTest {

    private val mockBookRepository = Mockito.mock(BookRepository::class.java)
    private val bookService = BookService(mockBookRepository)

    @Test
    fun `should find and return given book`() {
        //given
        val expected = Book("1", "Dave", "How to", "228")
        //when
        `when`(mockBookRepository.findById(expected.id)).thenReturn(Mono.just(expected))
        //then
        StepVerifier
            .create(bookService.findById(expected.id))
            .expectNext(expected)
            .expectComplete()
            .verify()
    }
}