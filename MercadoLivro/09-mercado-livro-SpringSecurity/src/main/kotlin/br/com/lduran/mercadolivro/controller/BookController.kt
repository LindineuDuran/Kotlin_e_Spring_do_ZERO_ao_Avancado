package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.request.PostBookRequest
import br.com.lduran.mercadolivro.controller.request.PutBookRequest
import br.com.lduran.mercadolivro.controller.response.BookResponse
import br.com.lduran.mercadolivro.controller.response.PageResponse
import br.com.lduran.mercadolivro.extension.toBookModel
import br.com.lduran.mercadolivro.extension.toPageResponse
import br.com.lduran.mercadolivro.extension.toResponse
import br.com.lduran.mercadolivro.service.BookService
import br.com.lduran.mercadolivro.service.CustomerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/books")
@SecurityRequirement(name = "Bearer Authentication")
class BookController(private val bookService : BookService,
                     private val customerService: CustomerService) {
    @GetMapping
    fun findAll(@PageableDefault(page= 0, size= 10) pageable: Pageable, @RequestParam(required = false) name: String?): PageResponse<BookResponse> {
        return bookService.findAll(pageable,name).map { it.toResponse() }.toPageResponse()
    }

    @GetMapping("/actives")
    fun findActives(@PageableDefault(page= 0, size= 10) pageable: Pageable): PageResponse<BookResponse> = bookService.findActives(pageable).map { it.toResponse()}.toPageResponse()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): BookResponse {
        return bookService.findById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid book: PostBookRequest) {
        val customer= customerService.findById(book.customerId)
        bookService.create(book.toBookModel(customer))
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    fun createList(@RequestBody @Valid books: List<PostBookRequest>) {
        bookService.createList(books)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid book: PutBookRequest) {
        val bookSaved= bookService.findById(id)
        bookService.update(book.toBookModel(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        bookService.delete(id)
    }
}