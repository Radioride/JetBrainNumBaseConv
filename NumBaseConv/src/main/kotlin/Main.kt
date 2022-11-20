package converter

import java.math.BigDecimal

// Do not delete this line
//
//fun fromDecimalToBase(a: BigInteger, b: BigInteger) {
//    var res = ""
//    var aa = a
//    while (aa > BigInteger.ZERO) {
//        res += "%x".format(aa.mod(b))
//        aa = aa.divide(b)
//    }
//    println("Conversion result: "+res.reversed())
//}

//fun fromDecimal(){
//    println("Enter number in decimal system:")
//    var decsys = readln().toInt()
//    println("Enter target base:")
//    val target = readln().toInt()
//    var res = ""
//    while (decsys > 0) {
//        res += "%x".format(decsys%target)
//        decsys /= target
//    }
//    println("Conversion result: "+res.reversed())
//}
//
//fun toDecimal(){
//    println("Enter source number:")
//    val src = readln()
//    println("Enter source base:")
//    val target = readln().toInt()
//    println("Conversion to decimal result: "+src.toInt(target))
//}

const val ALPHABETCONVERT = "0123456789abcdefghijklmnopqrstuvwxyz"
fun convertAny(decTarget: List<String>, optionOpi1: String): String {
    var buffDec: BigDecimal = BigDecimal.ONE
    var decNumber: BigDecimal = BigDecimal.ZERO
    for (i in optionOpi1.lastIndex downTo 0) {
        val octaNum = ALPHABETCONVERT.indexOf(optionOpi1[i]).toBigDecimal()
        decNumber += octaNum * buffDec
        buffDec *= decTarget[0].toBigDecimal()
    }
    val tarReform = MutableList(0) {""}
    do {
//        println(decNumber)
//        println((decNumber % decTarget[1].toBigDecimal()).toInt())
        tarReform.add(ALPHABETCONVERT[(decNumber % decTarget[1].toBigDecimal()).toInt()].toString())
        decNumber = decNumber.divideToIntegralValue(decTarget[1].toBigDecimal())
    } while(decNumber != BigDecimal.ZERO )
    return tarReform.reversed().joinToString("")
}
fun convertAny(decTarget: List<String>, optionOpi1: List<String>): String {
    val sint = convertAny(decTarget,optionOpi1[0])
    if (optionOpi1.size == 1) return sint
    var sfrac = convertAny(decTarget,optionOpi1[1])
    if (sfrac == "0") sfrac = "00000"
    return "$sint.$sfrac"
}

fun convert(astr: String){
    val srcbases = astr.split(" ").map { it.toInt() }
    if (srcbases.size < 2) return
    while (true) {
        println("Enter number in base ${srcbases[0]} to convert to base ${srcbases[1]} (To go back type /back)")
        when (val rds = readln()) {
            "/back" -> return
            else -> {
                print("Conversion result: ${ convertAny(astr.split(" "), rds.split(".")) }\n\n")
//                println("Conversion result: "+rds.split(".").map { it.toBigDecimal()gInteger(srcbases[0]).toString(srcbases[1]) }.joinToString(".") )
            }
        }
    }
}

fun main(args: Array<String>) {
    while (true) {
//        println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)")
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        when (val rds = readln()) {
            "/exit" -> return
            else -> convert(rds)
        }
    }
}