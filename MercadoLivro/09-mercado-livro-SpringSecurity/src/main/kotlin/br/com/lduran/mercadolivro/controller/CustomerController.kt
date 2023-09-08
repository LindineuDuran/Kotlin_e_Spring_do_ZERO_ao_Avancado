package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.request.PostCustomerRequest
import br.com.lduran.mercadolivro.controller.request.PutCustomerRequest
import br.com.lduran.mercadolivro.controller.response.CustomerResponse
import br.com.lduran.mercadolivro.controller.response.PageResponse
import br.com.lduran.mercadolivro.extension.toCustomerModel
import br.com.lduran.mercadolivro.extension.toCustomerModelList
import br.com.lduran.mercadolivro.extension.toPageResponse
import br.com.lduran.mercadolivro.extension.toResponse
import br.com.lduran.mercadolivro.security.UserCanOnlyAccessTheirOwnResource
import br.com.lduran.mercadolivro.service.CustomerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService : CustomerService) {
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    fun findAll(@PageableDefault(page= 0, size= 10) pageable: Pageable,
                @RequestParam(required = false) name: String?): PageResponse<CustomerResponse> {
        return customerService.findAll(pageable,name).map { it.toResponse() }.toPageResponse()
    }

    @GetMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    @SecurityRequirement(name = "Bearer Authentication")
    fun findById(@PathVariable id: Int): CustomerResponse {
        return customerService.findById(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    fun createList(@RequestBody @Valid customers: List<PostCustomerRequest>) {
        customerService.createList(customers.toCustomerModelList())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "Bearer Authentication")
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }
}