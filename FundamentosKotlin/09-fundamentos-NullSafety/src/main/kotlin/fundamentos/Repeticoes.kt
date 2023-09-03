package fundamentos

fun main(){
    print1a10()
    println()
    print10a1()
    println()
    printPar1a10()
    println()
    printRange(10, 20)
    println()
    whileMenorQue10()
    println()
    doWhileMenorQue10()
    println()
}

fun print1a10(){
    for(numero in 1..10){ println(numero) }
}
fun print10a1(){
    for(numero in 10 downTo 1){ println(numero) }
}

fun printPar1a10(){
    for(numero in 2..10 step 2){ println(numero) }
}

fun printRange(inicio: Int, fim: Int){
    for(numero in inicio..fim){ println(numero) }
}

fun whileMenorQue10() {
    var x = 0
    while(x < 10) {
        println(x)
        x++
    }
}

fun doWhileMenorQue10() {
    var x = 0
    do {
        println(x)
        x++
    }while(x < 10)
}