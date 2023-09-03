package fundamentos

fun main(){
    val pessoa:Pessoa? = null

    println(pessoa!!.nome) //Exception in thread "main" java.lang.NullPointerException
    println("Final do código")
}