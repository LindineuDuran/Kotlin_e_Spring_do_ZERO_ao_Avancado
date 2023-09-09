package br.com.lduran.mercadolivro.service

import br.com.lduran.mercadolivro.event.PurchaseEvent
import br.com.lduran.mercadolivro.helper.buildCustomer
import br.com.lduran.mercadolivro.helper.buildPurchase
import br.com.lduran.mercadolivro.helper.buildPurchaseList
import br.com.lduran.mercadolivro.repository.PurchaseRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

@ExtendWith(MockKExtension::class)
class PurchaseServiceTest{
    @MockK
    private lateinit var purchaseRepository: PurchaseRepository

    @MockK
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    @InjectMockKs
    private lateinit var purchaseService: PurchaseService

    val purchaseEventSlot = slot<PurchaseEvent>()

    @Test
    fun `shoud return purchases when customer is informed`() {
        val pageable = PageRequest.of(0, 10)

        val fakeCustomer = buildCustomer(id = 1)
        val fakePurchases = PageImpl(buildPurchaseList(3), pageable, 2)

        every { purchaseRepository.findByCustomer(pageable, fakeCustomer) } returns fakePurchases

        val purchases = purchaseService.findByCustomer(pageable, fakeCustomer)

        assertEquals(fakePurchases, purchases)
        verify(exactly = 1) { purchaseRepository.findByCustomer(pageable, any()) }
        verify(exactly = 0) { purchaseRepository.findAll(pageable) }
    }

    @Test
    fun `should create purchase and publish event`() {
        val purchase = buildPurchase()

        every { purchaseRepository.save(purchase) } returns purchase

        every { applicationEventPublisher.publishEvent(any()) } just runs

        purchaseService.create(purchase)

        verify(exactly = 1) { purchaseRepository.save(purchase) }
        verify(exactly = 1) { applicationEventPublisher.publishEvent(capture(purchaseEventSlot)) }

        assertEquals(purchase, purchaseEventSlot.captured.purchaseModel)
    }

    @Test
    fun `should update purchase`() {
        val purchase = buildPurchase()

        every { purchaseRepository.save(purchase) } returns purchase

        purchaseService.update(purchase)

        verify(exactly = 1) { purchaseRepository.save(purchase) }
    }
}