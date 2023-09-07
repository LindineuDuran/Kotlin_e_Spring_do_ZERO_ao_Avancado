package br.com.lduran.mercadolivro.service

import br.com.lduran.mercadolivro.enums.Errors
import br.com.lduran.mercadolivro.event.PurchaseEvent
import br.com.lduran.mercadolivro.exception.BadRequestException
import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.model.PurchaseModel
import br.com.lduran.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    fun findByCustomer(@PageableDefault(page= 0, size= 10) pageable: Pageable, customer: CustomerModel): Page<PurchaseModel> {
        return purchaseRepository.findByCustomer(pageable, customer)
    }

    fun create(purchaseModel: PurchaseModel){
        val unavailableBooks = purchaseModel.books.filter { it.isNotAvailable(it.status!!) }
        //unavailableBooks.forEach({throw BadRequestException(Errors.ML103.message.format(it.id), Errors.ML103.code)})

        val concatenado: String
        if(unavailableBooks.isNotEmpty()){
            concatenado = unavailableBooks.map { it.id }.joinToString(",")
            throw BadRequestException(Errors.ML103.message.format(concatenado), Errors.ML103.code)
        }
        purchaseRepository.save(purchaseModel)

        println("Disparando evento de compra")
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
        println("Finalização do processamento!")
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }
}