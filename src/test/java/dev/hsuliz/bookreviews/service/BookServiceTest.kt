package dev.hsuliz.bookreviews.service

import dev.hsuliz.bookreviews.model.Book
import dev.hsuliz.bookreviews.repository.BookRepository
import dev.hsuliz.bookreviews.util.exception.BookNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

public class BookServiceTest {

    private val mockBookRepository = Mockito.mock(BookRepository::class.java)
    private val bookService = BookService(mockBookRepository)


    @Nested
    inner class FindByIdTestClass {

        @Test
        fun `should find and return given book`() {
            //given
            val expected = Book("1", "Dave", "How to", "228")
            `when`(mockBookRepository.findById(expected.id)).thenReturn(Mono.just(expected))
            //then, when
            StepVerifier
                .create(bookService.findBookById(expected.id))
                .expectNext(expected)
                .expectComplete()
                .verify()
        }

        @Test
        fun `shouldn throw exception when book not found`() {
            //then, when
            `when`(mockBookRepository.findById(anyString())).thenReturn(Mono.empty())
            StepVerifier
                .create(bookService.findBookById("7777"))
                .expectError(BookNotFoundException::class.java)
                .verify()
        }
    }

    @Nested
    inner class FindAllBooksTestClass {

        @Test
        fun `should return list of books when invoked`() {
            //given
            val givenBooks = listOf(
                Book("1", "Dave", "How to", "228"),
                Book("2", "Daniel", "Why to", "9234"),
                Book("3", "Danny", "Should to", "6531")
            )
            `when`(mockBookRepository.findAll()).thenReturn(Flux.fromIterable(givenBooks))
            //then, when
            StepVerifier
                .create(bookService.findAllBooks())
                .also { verifier ->
                    givenBooks.forEach { book -> verifier.assertNext { foundBook -> assertEquals(book, foundBook) } }
                }
                .verifyComplete()
        }

        @Test
        fun `should return empty list when invoked`() {
            //given
            val givenEmptyBookList = listOf<Book>()
            `when`(mockBookRepository.findAll()).thenReturn(Flux.fromIterable(givenEmptyBookList))
            //then, when
            StepVerifier
                .create(bookService.findAllBooks())
                .verifyComplete()
        }
    }

}