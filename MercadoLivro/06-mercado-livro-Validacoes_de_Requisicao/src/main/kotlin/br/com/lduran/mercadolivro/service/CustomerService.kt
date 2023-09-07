package br.com.lduran.mercadolivro.service

import br.com.lduran.mercadolivro.enum.CustomerStatus
import br.com.lduran.mercadolivro.enums.Errors
import br.com.lduran.mercadolivro.exception.NotFoundException
import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository,
                      val bookService: BookService) {
    fun findAll(@PageableDefault(page= 0, size= 10) pageable: Pageable, name: String?): Page<CustomerModel> {
        name?.let { return customerRepository.findByNameContaining(pageable, it) }
        return customerRepository.findAll(pageable)
    }

    fun findById(id: Int): CustomerModel { return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) }}

    fun create(customer: CustomerModel) { customerRepository.save(customer) }

    fun createList(customersNew: List<CustomerModel>) {
        for (customer in customersNew) {
            create(customer)
        }
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)){ throw NotFoundException(Errors.ML201.message.format(customer.id), Errors.ML201.code) }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}