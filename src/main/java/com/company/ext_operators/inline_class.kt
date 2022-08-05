package com.company.ext_operators

data class User(val name: String, val birthday: Birthday)

@JvmInline
value class Birthday(val value: Int) {
    val isValid: Boolean
        get() = value>0
}

class Test6 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val p = User("Ivan", Birthday(23))
            val p2 = User("Stas",Birthday(-1))

            println("$p - ${p.birthday.isValid}")
            println("$p2 - ${p2.birthday.isValid}")
        }
    }
}