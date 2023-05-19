package dev.hsuliz.bookreviews.service

import dev.hsuliz.bookreviews.model.Book
import dev.hsuliz.bookreviews.repository.BookRepository
import dev.hsuliz.bookreviews.exception.BookNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@ExtendWith(MockitoExtension::class)
class BookServiceUnitTest {
    @Mock
    lateinit var bookRepositoryMock: BookRepository

    @InjectMocks
    lateinit var bookService: BookService

    @Nested
    inner class FindByIdTest {
        @Test
        fun `should find and return given book`() {
            val expected = Book("1", "Dave", "How to", "228", "12", "123", listOf())
            `when`(bookRepositoryMock.findById(expected.id)).thenReturn(Mono.just(expected))
            StepVerifier
                .create(bookService.findBookById(expected.id))
                .expectNext(expected)
                .verifyComplete()
        }

        @Test
        fun `shouldn throw exception when book not found`() {
            `when`(bookRepositoryMock.findById(anyString())).thenReturn(Mono.empty())
            StepVerifier
                .create(bookService.findBookById(anyString()))
                .expectError(BookNotFoundException::class.java)
                .verify()
        }
    }

    @Nested
    inner class FindAllBooksTest {
        @Test
        fun `should return list of books when invoked`() {
            val givenBooks = listOf(
                Book("1", "Dave", "How to", "228", "123", "123", listOf()),
                Book("2", "Daniel", "Why to", "9234", "123", "123", listOf()),
                Book("3", "Danny", "Should to", "6531", "123", "123", listOf())
            )
            `when`(bookRepositoryMock.findAll()).thenReturn(Flux.fromIterable(givenBooks))
            StepVerifier
                .create(bookService.findAllBooks())
                .also { verifier ->
                    givenBooks.forEach { book -> verifier.assertNext { foundBook -> assertEquals(book, foundBook) } }
                }
                .verifyComplete()
        }

        @Test
        fun `should return empty list when invoked`() {
            `when`(bookRepositoryMock.findAll()).thenReturn(Flux.empty())
            StepVerifier
                .create(bookService.findAllBooks())
                .expectNextCount(0)
                .verifyComplete()
        }
    }
}