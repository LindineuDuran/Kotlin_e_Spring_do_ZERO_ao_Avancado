package br.com.lduran.mercadolivro.controller.mapper;

import br.com.lduran.mercadolivro.controller.request.PostPurchaseRequest
import br.com.lduran.mercadolivro.controller.response.BookResponse
import br.com.lduran.mercadolivro.controller.response.CustomerResponse
import br.com.lduran.mercadolivro.controller.response.PurchaseResponse
import br.com.lduran.mercadolivro.model.PurchaseModel
import br.com.lduran.mercadolivro.service.BookService
import br.com.lduran.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(request.customerId)
        val books = bookService.findAllByIds(request.bookIds)
        books.map { }
        val purchase = PurchaseModel(customer = customer,
            books = books.toMutableList(),
            price = books.sumOf { it.price })

        return purchase
    }

    fun toResponse(purchase: PurchaseModel): PurchaseResponse {
        val customer = CustomerResponse(
            id = purchase.customer!!.id,
            name = purchase.customer!!.name,
            email = purchase.customer!!.email,
            status = purchase.customer!!.status
        )

        var books: MutableList<BookResponse> = mutableListOf()
        purchase.books.map {
            val customerTemp = CustomerResponse(id = it.customer!!.id,
                                                name= it.customer!!.name,
                                                email= it.customer!!.email,
                                                status= it.customer!!.status)
            val book = BookResponse(name = it.name, price = it.price, customer = customerTemp, status = it.status)

            books.add(book)
        }

        return PurchaseResponse(
            id = purchase.id,
            customer = customer,
            books = books,
            nfe = purchase.nfe,
            price = purchase.price,
            createdAt = purchase.createdAt
        )
    }
}
