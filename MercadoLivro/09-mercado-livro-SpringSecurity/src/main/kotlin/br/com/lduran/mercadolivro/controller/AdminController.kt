package br.com.lduran.mercadolivro.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Admin", description = "Endpoints for Managing Admin")
class AdminController {
    @GetMapping("/reports")
    @Operation(summary = "Finds all Reports", description = "Finds all Reports", tags = arrayOf("Admin"))
    fun report(): String {
        return "This is a report. Only admins can see it!"
    }

    /*@GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerResponse {
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
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val customerSaved= customerService.findById(id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id)
    }*/
}
