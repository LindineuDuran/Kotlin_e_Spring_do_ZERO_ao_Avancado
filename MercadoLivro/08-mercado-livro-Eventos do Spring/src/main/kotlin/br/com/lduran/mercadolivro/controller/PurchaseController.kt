package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.mapper.PurchaseMapper
import br.com.lduran.mercadolivro.controller.request.PostPurchaseRequest
import br.com.lduran.mercadolivro.controller.response.PurchaseResponse
import br.com.lduran.mercadolivro.service.CustomerService
import br.com.lduran.mercadolivro.service.PurchaseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchases", description = "Endpoints for Managing Purchases")
class PurchaseController(private val purchaseService: PurchaseService,
                         private val purchaseMapper: PurchaseMapper,
                         private val customerService: CustomerService
) {

    @GetMapping("/{id}")
    @Operation(summary = "Finds all Purchases for a Customer", description = "Finds all Purchases for a Customers", tags = arrayOf("Purchases"))
    fun findAllByCustomer(@PageableDefault(page= 0, size= 10) pageable: Pageable, @PathVariable id: Int): Page<PurchaseResponse> {
        val customer = customerService.findById(id)
        return purchaseService.findByCustomer(pageable,customer).map { purchaseMapper.toResponse(it) }}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adds a new Purchase",
               description = "Adds a new Purchase by passing in a JSON representation of the purchase!",
               tags = arrayOf("Purchases"))
    fun create(@RequestBody @Valid request: PostPurchaseRequest) {
        purchaseService.create(purchaseMapper.toModel(request))
    }
}