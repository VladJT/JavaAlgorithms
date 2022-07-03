package com.company

class Kotlin {
    companion object {

        class ClassX {
            val funField = fun(s: String): Int {
                println("$s получен")
                return 1
            }
        }

        class ClassY {
            var  name:String = ""
            fun printname(){
                println(this.name)
            }
            // анонимная функция
            var funField = fun(s: String): Int {
                if (s.length > 5) return 1
                else return 0
            }

            // лямбда
            // name@ - даем алиас лямбде, для организации возможности нескольких return
            var funFieldL = hacked@{ s: String ->
                if (s.length > 5) return@hacked 1
                else return@hacked 0
            }


            fun setFun(block: (s: String) -> Int) {
                funField = block
            }

            fun math(argFunType : (Int,Int)->Int ,a:Int, b:Int):Unit{
                println(argFunType.invoke(a,b))
            }
        }

        // sum, sum2 - переменные фугкционального типа (ссылаются на функцию)
        val sum = {a:Int, b: Int -> a+b}   // лямбда
        val div = {a:Int, b: Int -> a/b}   // лямбда
        val sum2 = fun(a:Int,b:Int):Int{return a+b} // анонимная функция

        // функция расширение
        fun ClassY.getName():String{
            return "name = "+ this.name// видим поле класса ClassY
        }

        // фунция высшего порядка (принимает или возвращает функциональный тип)
        fun highOrderFunction(f: (i:Int)->Double, c:Char): (_:Int)->Double{
            return {int: Int -> int.toDouble()}
        }


        @JvmStatic
        fun main(args: Array<String>) {
            var l3 = ClassX()
            var l4 = ClassY()
            l4.setFun(l3.funField)
            l4.name = "LESSON 04 EX"



            println(l4.getName())
         //   l4.math(div, 12,4)
        }




    }

}