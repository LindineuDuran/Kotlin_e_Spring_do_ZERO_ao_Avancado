package br.com.lduran.mercadolivro.event.listener

import br.com.lduran.mercadolivro.event.PurchaseEvent
import br.com.lduran.mercadolivro.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UpdateSoldBookListener(private val bookService: BookService) {
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        println("Atualizando status dos livros")
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}