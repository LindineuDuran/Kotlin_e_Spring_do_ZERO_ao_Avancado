package br.com.lduran.mercadolivro.event.listener

import br.com.lduran.mercadolivro.event.PurchaseEvent
import br.com.lduran.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(private val purchaseService: PurchaseService) {
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent){
        println("Gerando NFE")
        val nfe = UUID.randomUUID().toString()
        val purchaseModel =  purchaseEvent.purchaseModel.copy(nfe = nfe)

        purchaseService.update(purchaseModel)
    }
}