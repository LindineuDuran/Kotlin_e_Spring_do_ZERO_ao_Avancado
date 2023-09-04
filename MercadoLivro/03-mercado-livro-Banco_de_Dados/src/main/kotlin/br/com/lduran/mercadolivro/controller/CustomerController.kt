package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.request.PostCustomerRequest
import br.com.lduran.mercadolivro.controller.request.PutCustomerRequest
import br.com.lduran.mercadolivro.extension.toCustomerModel
import br.com.lduran.mercadolivro.extension.toCustomerModelList
import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController(val customerService : CustomerService) {
    @GetMapping
    fun findAll(@RequestParam name: String?): List<CustomerModel> {
        return customerService.getAll(name)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel {
        return customerService.findById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    fun createList(@RequestBody customers: List<PostCustomerRequest>) {
        customerService.createList(customers.toCustomerModelList())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: PutCustomerRequest) {
        customerService.update(customer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}