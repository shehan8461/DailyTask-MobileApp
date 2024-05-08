package com.example.dailytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class TaskViews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_views)

        val submit_btn=findViewById<Button>(R.id.btn_addtask)
        val taskname=findViewById<TextView>(R.id.taskname)
        val taskdescription=findViewById<TextView>(R.id.taskdes)
        val date=findViewById<TextView>(R.id.date)
        val category=findViewById<TextView>(R.id.category)
        val context=this
        submit_btn.setOnClickListener({
            if(taskname.text.toString().length>0&&
                taskdescription.text.toString().length>0){
                var user=Task(taskname.text.toString(),taskdescription.text.toString(),date.text.toString(),category.text.toString())
                var db=DataBaseHandler(context)
                db.insertdata(user)


            }else{
                Toast.makeText(context,"please fill all data's", Toast.LENGTH_SHORT).show()
            }
        })

    }
}