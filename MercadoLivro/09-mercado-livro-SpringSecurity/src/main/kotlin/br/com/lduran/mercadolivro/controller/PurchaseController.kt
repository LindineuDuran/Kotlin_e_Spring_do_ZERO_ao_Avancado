package br.com.lduran.mercadolivro.controller

import br.com.lduran.mercadolivro.controller.mapper.PurchaseMapper
import br.com.lduran.mercadolivro.controller.request.PostPurchaseRequest
import br.com.lduran.mercadolivro.controller.response.PageResponse
import br.com.lduran.mercadolivro.controller.response.PurchaseResponse
import br.com.lduran.mercadolivro.extension.toPageResponse
import br.com.lduran.mercadolivro.service.CustomerService
import br.com.lduran.mercadolivro.service.PurchaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/purchases")
class PurchaseController(private val purchaseService: PurchaseService,
                         private val purchaseMapper: PurchaseMapper,
                         private val customerService: CustomerService
) {

    @GetMapping("/{id}")
    fun findAllByCustomer(@PageableDefault(page= 0, size= 10) pageable: Pageable, @PathVariable id: Int): PageResponse<PurchaseResponse> {
        val customer = customerService.findById(id)
        return purchaseService.findByCustomer(pageable,customer).map { purchaseMapper.toResponse(it) }.toPageResponse()}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostPurchaseRequest) {
        purchaseService.create(purchaseMapper.toModel(request))
    }
}