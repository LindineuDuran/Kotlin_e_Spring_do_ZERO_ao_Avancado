package br.com.lduran.mercadolivro.service

import br.com.lduran.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {
    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun getCustomer(id: String): CustomerModel {
        return customers.filter { it.id == id }.first()
    }

    fun create(customer: CustomerModel) {
        val id = if(customers.isEmpty()) {
            1
        } else{
            customers.last().id!!.toInt() + 1
        }.toString()

        customer.id = id

        customers.add(customer)
    }

    fun update(customer: CustomerModel) {
        customers.filter { it.id == customer.id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        customers.removeIf { it.id == id }
    }
}