package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.request.PostCustomerRequest
import br.com.lduran.mercadolivro.controller.request.PutCustomerRequest
import br.com.lduran.mercadolivro.controller.response.CustomerResponse
import br.com.lduran.mercadolivro.extension.toCustomerModel
import br.com.lduran.mercadolivro.extension.toCustomerModelList
import br.com.lduran.mercadolivro.extension.toResponse
import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
@Tag(name = "Customers", description = "Endpoints for Managing Customers")
class CustomerController(val customerService : CustomerService) {
    @GetMapping
    @Operation(summary = "Finds all Customers", description = "Finds all Customers")
    fun findAll(@PageableDefault(page= 0, size= 10) pageable: Pageable,
                @RequestParam(required = false) name: String?): Page<CustomerResponse> {
        return customerService.findAll(pageable,name).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a Customer", description = "Finds a Customer")
    fun findById(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adds a new Customer",
        description = "Adds a new Customer by passing in a JSON representation of the customer!")
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adds a list of new Customers", description = "Adds a list of new Customers")
    fun createList(@RequestBody @Valid customers: List<PostCustomerRequest>) {
        customerService.createList(customers.toCustomerModelList())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Updates a Customer",
        description = "Updates a Customer by passing in a JSON representation of the customer!")
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a Customer",
        description = "Deletes a Customer by passing in a JSON, XML or YML representation of the customer!")
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}