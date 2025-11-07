package com.appweek09

import java.util.Date
import java.util.UUID

class Student(
    val name: String,
    val id: String = UUID.randomUUID().toString(),
    val department: String = "Computer Science",
    val grade: String = "2nd",
    val email: String = "",
    val addedData: Date = Date()
){
    override fun toString(): String = name
}