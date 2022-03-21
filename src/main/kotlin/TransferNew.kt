const val LIMIT_CARD = 600_000_00
const val LIMIT_VK_PAY_MONTH = 40_000_00
const val LIMIT_VK_PAY_TRANSFER = 15_000_00
const val TAX_FREE_CARD = 75_000_00
const val COMMISSION_MAESTRO_MASTERCARD = 0.006
const val COMMISSION_VISA_MIR = 0.0075


fun main() {
    val typeOfCard = "VK Pay"
    val amountMonth = 0
    val amountPay = 100_00

    val checkOfTransfer = check(typeOfCard, amountMonth, amountPay)
    val commission = calculation(typeOfCard, amountMonth, amountPay)
    if (checkOfTransfer == true) {
        println("Комиссия за перевод составляет $commission")
    } else {
        println("Ваш лимит на перевод исчерпан")
    }

}

fun check(typeOfCard: String, amountMonth: Int, amountPay: Int): Boolean {

    return when (typeOfCard) {
        "VK Pay" -> if (amountMonth <= LIMIT_VK_PAY_MONTH && amountPay <= LIMIT_VK_PAY_TRANSFER) true else false
        "Mastercard", "Maestro", "Visa", "Mir" -> if (amountMonth <= LIMIT_CARD) true else false
        else -> false
    }
}

fun calculation(typeOfCard: String, amountMonth: Int, amountPay: Int): Int {
  val discount = when (typeOfCard) {
        "VK Pay" -> 0
        "Mastercard", "Maestro" -> if (amountMonth <= TAX_FREE_CARD) 0 else (amountPay * COMMISSION_MAESTRO_MASTERCARD + 20_00).toInt()
        "Visa", "Mir" -> if (amountPay > 35_00) (amountPay * COMMISSION_VISA_MIR).toInt() else 35_00
        else -> error("Такой платежной системы не существует")
    }

}

