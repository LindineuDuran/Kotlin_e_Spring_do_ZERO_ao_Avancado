package br.com.lduran.mercadolivro.service

import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository) {
    fun getAll(name: String?): List<CustomerModel> {
        name?.let { return customerRepository.findByNameContaining(it) }
        return customerRepository.findAll().toList()
    }

    fun findById(id: Int): CustomerModel { return customerRepository.findById(id).orElseThrow() }

    fun create(customer: CustomerModel) { customerRepository.save(customer) }

    fun createList(customersNew: List<CustomerModel>) {
        for (customer in customersNew) {
            create(customer)
        }
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){ throw Exception() }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        if(!customerRepository.existsById(id)){ throw Exception() }

        customerRepository.deleteById(id)
    }
}