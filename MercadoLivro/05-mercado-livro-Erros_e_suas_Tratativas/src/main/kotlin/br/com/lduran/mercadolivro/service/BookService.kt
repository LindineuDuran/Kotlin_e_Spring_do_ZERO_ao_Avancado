package br.com.lduran.mercadolivro.service

import br.com.lduran.mercadolivro.controller.request.PostBookRequest
import br.com.lduran.mercadolivro.enum.BookStatus
import br.com.lduran.mercadolivro.enums.Errors
import br.com.lduran.mercadolivro.exception.NotFoundException
import br.com.lduran.mercadolivro.extension.toBookModel
import br.com.lduran.mercadolivro.model.BookModel
import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.repository.BookRepository
import br.com.lduran.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class BookService(val bookRepository: BookRepository,
                  val customerRepository: CustomerRepository
) {
    fun findAll(pageable: Pageable, name: String?): Page<BookModel> {
        name?.let { return bookRepository.findByNameContaining(pageable, it) }
        return bookRepository.findAll(pageable)
    }

    fun findById(id: Int): BookModel { return bookRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code) }}

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(pageable, BookStatus.ATIVO)
    }

    fun findAllByIds(bookIds: Set<Int>): List<BookModel> {
        return bookRepository.findAllById(bookIds).toList()
    }

    fun create(book: BookModel) { bookRepository.save(book) }

    fun createList(books: List<PostBookRequest>) {
        books.forEach {
            val customer = customerRepository.findById(it.customerId).orElseThrow{ NotFoundException(Errors.ML101.message.format(it.customerId), Errors.ML101.code) }
            val book = it.toBookModel(customer)
            bookRepository.save(book)
        }
    }

    fun update(book: BookModel) {bookRepository.save(book)}

    fun delete(@PathVariable id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for(book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}