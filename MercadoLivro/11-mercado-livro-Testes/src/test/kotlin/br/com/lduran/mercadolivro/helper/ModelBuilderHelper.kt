package br.com.lduran.mercadolivro.helper

import br.com.lduran.mercadolivro.enum.CustomerStatus
import br.com.lduran.mercadolivro.enum.Role
import br.com.lduran.mercadolivro.model.BookModel
import br.com.lduran.mercadolivro.model.CustomerModel
import br.com.lduran.mercadolivro.model.PurchaseModel
import java.math.BigDecimal
import java.util.*

fun buildBook(
    id : Int? = null, name: String = "Livro ${id}", price : BigDecimal = BigDecimal.TEN, customer: CustomerModel = buildCustomer()
) = BookModel(id = id, name = name, price = price, customer = customer)

fun buildBookList(quantidade: Int): List<BookModel>
{
    val books = ArrayList<BookModel>()

    if(quantidade > 1){for (i  in 1..quantidade) books.add(buildBook(id = i))}
    else {books.add(buildBook(id = 1))}

    return books
}

fun buildCustomer(
    id: Int? = null, name: String = "customer name ${UUID.randomUUID()}", email: String = "${UUID.randomUUID()}@email.com", password: String = "password"
) = CustomerModel(id = id, name = name, email = email, status = CustomerStatus.ATIVO, password = password, roles = setOf(Role.CUSTOMER))

fun buildCustomerList(quantidade: Int): List<CustomerModel>
{
    val customers = ArrayList<CustomerModel>()

    if(quantidade > 1){for (i  in 1..quantidade) customers.add(buildCustomer(id = i))}
    else {customers.add(buildCustomer(id = 1))}

    return customers
}

fun buildPurchase(
    id: Int? = null, customer: CustomerModel = buildCustomer(), books: MutableList<BookModel> = mutableListOf(), nfe: String? = UUID.randomUUID().toString(), price: BigDecimal = BigDecimal.TEN
) = PurchaseModel (
    id = id, customer = customer, books = books, nfe = nfe, price = price
)

fun buildPurchaseList(quantidade: Int): List<PurchaseModel>
{
    val purchases = ArrayList<PurchaseModel>()

    if(quantidade > 1){for (i  in 1..quantidade) purchases.add(buildPurchase(id = i))}
    else {purchases.add(buildPurchase(id = 1))}

    return purchases
}