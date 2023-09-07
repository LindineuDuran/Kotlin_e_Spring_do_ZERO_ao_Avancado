package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.request.PostBookRequest
import br.com.lduran.mercadolivro.controller.request.PutBookRequest
import br.com.lduran.mercadolivro.controller.response.BookResponse
import br.com.lduran.mercadolivro.extension.toBookModel
import br.com.lduran.mercadolivro.extension.toBookModelList
import br.com.lduran.mercadolivro.extension.toResponse
import br.com.lduran.mercadolivro.model.BookModel
import br.com.lduran.mercadolivro.service.BookService
import br.com.lduran.mercadolivro.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Endpoints for Managing Books")
class BookController(val bookService : BookService,
                     val customerService: CustomerService) {
    @GetMapping
    @Operation(summary = "Finds all Book", description = "Finds all Book")
    fun findAll(@PageableDefault(page= 0, size= 10) pageable: Pageable, @RequestParam(required = false) name: String?): Page<BookResponse> {
        return bookService.findAll(pageable,name).map { it.toResponse() }
    }

    @GetMapping("/actives")
    @Operation(summary = "Finds all Enabled Books", description = "Finds all Enabled Books")
    fun findActives(@PageableDefault(page= 0, size= 10) pageable: Pageable): Page<BookResponse> = bookService.findActives(pageable).map { it.toResponse()}

    @GetMapping("/{id}")
    @Operation(summary = "Finds a Book", description = "Finds a Book")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adds a new Book", description = "Adds a new Book by passing in a JSON representation of the book!")
    fun create(@RequestBody @Valid book: PostBookRequest) {
        val customer= customerService.findById(book.customerId)
        bookService.create(book.toBookModel(customer))
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adds a list of new Books", description = "Adds a list of new Books")
    fun createList(@RequestBody @Valid books: List<PostBookRequest>) {
        bookService.createList(books)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates a Book", description = "Updates a Book by passing in a JSON representation of the book!")
    fun update(@PathVariable id: Int, @RequestBody @Valid book: PutBookRequest) {
        val bookSaved= bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a Book", description = "Deletes a Book by passing in a JSON representation of the book!")
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }
}