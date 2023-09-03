package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.request.PostCustomerRequest
import br.com.lduran.mercadolivro.controller.request.PutCustomerRequest
import br.com.lduran.mercadolivro.extension.toCustomerModel
import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController(val customerService : CustomerService) {
    val customers = mutableListOf<CustomerModel>()

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerModel> {
        return customerService.getAll(name)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: String): CustomerModel {
        return customerService.getCustomer(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: String, @RequestBody customer: PutCustomerRequest) {
        customerService.update(customer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: String) {
        customerService.delete(id)
    }
}