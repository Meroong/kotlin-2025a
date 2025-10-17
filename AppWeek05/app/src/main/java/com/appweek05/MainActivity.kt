package com.appweek05

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonAdd: Button //lateinit
    private lateinit var buttonClear: Button
    private lateinit var listView: ListView
    private lateinit var editTextStudent: EditText
    private lateinit var textViewCount: TextView
    //Collection
    private lateinit var studentList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    companion object {
        private const val TAG = "KotlinWeek05App"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: AppWeek05 started")

        setupView()
        setUpListView()
        setupListeners()

        addInitialData()

    }
    private fun setupView(){
        listView = findViewById(R.id.listViewStudents)
        editTextStudent = findViewById(R.id.editTextStudent)
        buttonClear = findViewById(R.id.buttonClear)
        buttonAdd = findViewById(R.id.buttonAdd)
        textViewCount = findViewById(R.id.textViewCount)

        studentList = ArrayList()
        Log.d(TAG, "Views initialized")
    }

    private fun setUpListView(){
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList) //보여지는 리스트 뷰와
        listView.adapter = adapter
        Log.d(TAG, "ListViews and Adapter setup completed")
    }

    //이벤트 처리기
    private fun setupListeners(){
        buttonAdd.setOnClickListener {
            addStudent()
        }
        buttonClear.setOnClickListener {
            clearAllStudent()
        }
        listView.setOnItemLongClickListener{
            _,_, position, _ -> removeStudent(position)
            true
        }
        listView.setOnItemClickListener{ _, _, position, _ ->
            val studentName = studentList[position]
            Toast.makeText(     //알림창? 인듯?
                this,
                "Selected: $studentName (position: ${position+1}",
                Toast.LENGTH_SHORT
            ).show() //메서드 체이닝
            Log.d(TAG, "Selected: $studentName at position (position: ${position}")
        }
        Log.d(TAG, "Event listener Completed")

    }

    private fun addStudent(){
        val studentName = editTextStudent.text.toString().trim()

        if(studentName.isEmpty()){
            Toast.makeText(
                this,
                "Please enter a student name",
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, "Attempted to add empty student name")
            return
        }

        if(studentList.contains(studentName)){
            Toast.makeText(
                this,
                "Student '$studentName' already exists",
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, "Attempted to add duplicate student name:$studentName")
            return
        }
        studentList.add(studentName) // 얘는 백에 추가 어뎁터로 뷰에 추가하도록
        adapter.notifyDataSetChanged() //변화에 대해 알리고
        editTextStudent.text.clear() //입력칸 지우고
        updateStudentCount()
        Toast.makeText(
            this,
            "Added: $studentName",
            Toast.LENGTH_SHORT
        ).show()
        Log.d(TAG, "Added student name: $studentName (Total: ${studentList.size}")
    }

    private fun clearAllStudent() {

        if (studentList.isEmpty()) {
            Toast.makeText(
                this,
                "List is already empty",
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, "Attempted to add empty student name")
            return
        }

        val count = studentList.size
        studentList.clear()
        adapter.notifyDataSetChanged()
        updateStudentCount()
        Toast.makeText(
            this,
            "Cleared all $count students",
            Toast.LENGTH_SHORT
        ).show()
        Log.d(TAG, "Cleared all students (Total cleared: $count)")
    }
    private fun removeStudent(position: Int) {
        if (position >= 0 && position < studentList.size) {
            val removedStudent = studentList.removeAt(position)
            adapter.notifyDataSetChanged()
            updateStudentCount()
            Toast.makeText(
                this,
                "Removed $removedStudent)",
                Toast.LENGTH_SHORT
            ).show()
            Log.d(TAG, "Removed: $removedStudent (Remaining ${studentList.size}")
        }
    }
    private fun updateStudentCount(){
        textViewCount.text = "Total Students: ${studentList.size}"
    }

    private fun addInitialData(){
        val initialStudent = listOf("Kim", "Lee", "Park")
        studentList.addAll(initialStudent)
        adapter.notifyDataSetChanged()
        updateStudentCount()
        Log.d(TAG, "Added initial data: $initialStudent ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Current student count: ${studentList.size}")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Saving state with ${studentList.size} students")
    }
    }
