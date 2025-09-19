package com.kotlinbasics

import android.os.Bundle
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

private fun week03Classes(){
    println("== Kotlin Classes ==")

    class Student{
        var name: String =""
        var age: Int = 0

        fun introduce(){
            println("Hi, I'm a $name and I'm $age years old")
        }
    }
    val student = Student()
    student.name = "Mirae"
    student.age = 21
    student.introduce()

    data class Person(val name: String, val age: Int)

    val person1 = Person("Kim", 23)
    val person2 = Person("kim", 23)
    println("Person1 : $person1")
    println("Equal? ${person1 == person2}")
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