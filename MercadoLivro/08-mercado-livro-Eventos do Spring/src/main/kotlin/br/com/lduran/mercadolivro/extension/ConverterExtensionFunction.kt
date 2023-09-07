package br.com.lduran.mercadolivro.extension

import br.com.lduran.mercadolivro.controller.request.PostBookRequest
import br.com.lduran.mercadolivro.controller.request.PostCustomerRequest
import br.com.lduran.mercadolivro.controller.request.PutBookRequest
import br.com.lduran.mercadolivro.controller.request.PutCustomerRequest
import br.com.lduran.mercadolivro.controller.response.BookResponse
import br.com.lduran.mercadolivro.controller.response.CustomerResponse
import br.com.lduran.mercadolivro.enum.BookStatus
import br.com.lduran.mercadolivro.enum.CustomerStatus
import br.com.lduran.mercadolivro.model.BookModel
import br.com.lduran.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer)
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name?:previousValue.name,
        price = this.price?:previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer)
}

fun BookModel.toResponse(): BookResponse {
    val customer = CustomerResponse(id = this.customer!!.id,
                                    name= this.customer!!.name,
                                    email= this.customer!!.email,
                                    status= this.customer!!.status)

    return BookResponse(id = this.id,
                        name= this.name,
                        price= this.price,
                        customer= customer,
                        status= this.status)
}

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun List<PostCustomerRequest>.toCustomerModelList(): List<CustomerModel> {
    val customers: MutableList<CustomerModel> = mutableListOf()

    this.forEach {
        customers.add(CustomerModel(name = it.name, email = it.email, status = CustomerStatus.ATIVO))
    }

    return customers
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
}

fun CustomerModel.toResponse(): CustomerResponse{
    return CustomerResponse(id = this.id,
                            name= this.name,
                            email= this.email,
                            status= this.status)
}