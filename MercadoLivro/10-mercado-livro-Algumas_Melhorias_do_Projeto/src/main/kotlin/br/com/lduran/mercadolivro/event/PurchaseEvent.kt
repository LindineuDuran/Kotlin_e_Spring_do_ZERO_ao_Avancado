package br.com.lduran.mercadolivro.event

import br.com.lduran.mercadolivro.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent (source: Any, val purchaseModel: PurchaseModel): ApplicationEvent(source)