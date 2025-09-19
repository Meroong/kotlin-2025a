package com.kotlinbasics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kotlinbasics.ui.theme.KotlinBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        week02Functions()
        week02Variable()
        week03Classes()
        week03Collections()
    }
}

private fun week03Collections(){
    println("== Kotlin Collections ==")

    val fruits = listOf("apple","banna","orange")
    val mutableFruits = mutableListOf("kiwi", "watermelon")

    //fruits.add("kiwi") 불변자료 리스트타입이라 add는 컴파일 에러 뮤터블과 차이
    println("Fruits : $fruits")
    mutableFruits.add("banana")
    println("Mutable fruits : $mutableFruits")

    val scores = mapOf("Kim" to 100, "Park" to 97, "Lee" to 99)
    println("Scores: $scores")

    for(fruit in mutableFruits){
        println("I like $fruits")
    }

    scores.forEach{(name, score)-> println("Name: $name, Score: $score")}
}

private fun week03Classes() {
    Log.d("KotlinWeek03","== Kotlin Classes")

    class Person(val name: String, var age: Int) {

        fun introduce() {
            Log.d("KotlinWeek03", "안녕하세요, $name ($age 세)입니다.")
        }
        fun birthday(){
            age++
            Log.d("KotlinWeek03", "$name 의 생일! 이제 $age 세...")


        }
    }
    val person1 = Person("홍길동", 27)
    person1.introduce()
    person1.birthday()

    open class Animal(var species: String){
        var weight: Double = 0.0
        constructor(species: String, weight: Double): this(species){
            this.weight = weight
            Log.d("KotlineWeek03", "$species 의 무게 : $weight kg")
        }
        open fun makeSound(){
            Log.d("KotlinWeek03", "$species 가 소리를 냅니다.")
        }
    }
    val puppy = Animal("강아지", 10.5)
    puppy.makeSound()
                //Animal은 상속될 수 없는 상황 open으로 상속가능하게 바꾸자
    class Dog(species: String, weight: Double, val breed: String) : Animal(species, weight){
        // 메서드도 open 키워드를 써야 상속이 가능함
        override fun makeSound(){
            Log.d("KotlinWeek03", "$breed($species)가 멍멍 짖습니다.")
        }
    }
    val dog = Dog("개", 12.5, "골든 리트리버")
    dog.makeSound()

    data class Book(val title: String, val author: String, val pages: Int){
        //data class의 특징
    }
    val book1 = Book("코틀린 입문","Kim",400)
    val book2 = Book("코틀린 입문","Kim",400)

    Log.d("KotlinWeek03", "book1 == book2: ${book1 == book2}")
    Log.d("KotlinWeek03", "book1: $book1")
    }

private fun week02Functions(){
    //println("Week 02: Functions")

    //fun greet(name: String) = "Hello, $name!"

    //println(greet("Android developer"))

    println("== Kotlin Functions ==")

    fun greet(name: String): String {
        return "Hello, $name!"
    }
    fun add(a: Int, b: Int) = a+b

    fun introduce(name: String, age: Int = 19){
        println("my name is $name and I'm $age years old")
    }
    println(greet("Kotlin"))
    println("Sum: ${add(5, -71)}")
    introduce("kim",  7)
    introduce("Park", 19)
}

private fun week02Variable(){
    println("Week02: Variables")
    var courseName = "Mobile Programming"
    //courseName = "Iot Programming"
    var week =2
    println("Course: $courseName")
    println("Week : $week")

    val age: Int = 24
    val height: Double = 177.7
    val isStudent: Boolean = false
    println("Age: $age, Height $height, Student: $isStudent")

    //var nickname: String = null
    var nickname: String? = null
    nickname = "mirae"
    println("Nickname: $nickname ${nickname?.length}")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinBasicsTheme {
        Greeting("Android")
    }
}