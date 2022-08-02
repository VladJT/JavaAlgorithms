package com.company.ext_operators

data class Money(val value: Int, val currency: Currency) {
    enum class Currency { RUB, EUR }

    // вариант 1
    operator fun minus(secondValue: Money): Money {
        if (this.currency == secondValue.currency) return Money(this.value - secondValue.value, this.currency)
        else {
            throw UnsupportedOperationException("Different currency! Can't substruct")
        }
    }

    // Функции, помеченные ключевым словом infix, могут вызываться с использованием инфиксной записи (без точки и скобок для вызова). Инфиксные функции должны соответствовать следующим требованиям:
    //Они должны являться членом другой функции или расширения;
    //В них должен использоваться только один параметр;
    //Параметр не должен принимать переменное количество аргументов и не должен иметь значения по умолчанию.
    infix fun sell(money: Money): Money {
        return this - money
    }
}

// вариант 2
operator fun Money.plus(secondValue: Money): Money {
    if (this.currency == secondValue.currency) return Money(this.value + secondValue.value, this.currency)
    else {
        throw UnsupportedOperationException("Different currency! Can't substruct")
    }
}

class Test{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val creditMoney = Money(100, Money.Currency.RUB)
            val incomeMoney = Money(50, Money.Currency.RUB)

            val ostatok = creditMoney - incomeMoney + Money(20, Money.Currency.RUB)
            println("itog = $ostatok")
        }
    }
}