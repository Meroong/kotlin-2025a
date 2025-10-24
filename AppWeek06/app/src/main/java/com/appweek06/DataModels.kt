package com.appweek06

import android.graphics.Color
import java.util.*

data class Student(
    val name: String,
    val id: String = UUID.randomUUID().toString(),
    val addedDate: Date = Date()
)
data class Task(
    val title: String,
    val describe: String="",
    val isCompleted: Boolean=false,
    val dueDate: Date? = null,
    val id: String = UUID.randomUUID().toString(),
    val createdDate: Date = Date(),
    val priority: TaskPriority
){
    override fun toString(): String {       //taskPriority 클래스를 만들었음
        val status = if(isCompleted) "V" else "O"
        val priorityIcon = when(priority){
            TaskPriority.HIGH -> "!!!"
            TaskPriority.MEDIUM -> "!!"
            TaskPriority.Low -> "!"
        }
        return "$status $priorityIcon $title"
    }

}
enum class TaskPriority(val displayName: String, val color:Int){
    HIGH("High", Color.RED),
    MEDIUM("Medium", Color.BLUE),
    Low("Low", Color.GREEN),
}
enum class Appmode(val displayName: String){
    STUDENT_LIST("Student List"),
    TASK_MANAGER("Task Manager"),
}
