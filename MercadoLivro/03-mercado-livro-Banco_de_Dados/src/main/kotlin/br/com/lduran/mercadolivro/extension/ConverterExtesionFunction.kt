package br.com.lduran.mercadolivro.extension

import br.com.lduran.mercadolivro.controller.request.PostCustomerRequest
import br.com.lduran.mercadolivro.controller.request.PutCustomerRequest
import br.com.lduran.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun List<PostCustomerRequest>.toCustomerModelList(): List<CustomerModel> {
    var customers: MutableList<CustomerModel> = mutableListOf()

    this.forEach {
        customers.add(CustomerModel(name = it.name, email = it.email))
    }

    return customers
}

fun PutCustomerRequest.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}
