package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.request.PostCustomerRequest
import br.com.lduran.mercadolivro.controller.request.PutCustomerRequest
import br.com.lduran.mercadolivro.controller.response.CustomerResponse
import br.com.lduran.mercadolivro.extension.toCustomerModel
import br.com.lduran.mercadolivro.extension.toCustomerModelList
import br.com.lduran.mercadolivro.extension.toResponse
import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customers")
class CustomerController(val customerService : CustomerService) {
    @GetMapping
    fun findAll(@PageableDefault(page= 0, size= 10) pageable: Pageable,
                @RequestParam(required = false) name: String?): Page<CustomerResponse> {
        return customerService.findAll(pageable,name).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
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
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}