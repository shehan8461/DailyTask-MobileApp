package com.example.dailytask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class TaskViews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_views)

        val submit_btn=findViewById<Button>(R.id.btn_addtask)
        val taskname=findViewById<TextView>(R.id.taskname)
        val taskdescription=findViewById<TextView>(R.id.taskdes)
        val date=findViewById<TextView>(R.id.date)
        val category=findViewById<TextView>(R.id.category)

        val getreadbtn=findViewById<Button>(R.id.readbtn)
        val getreadddetailsview=findViewById<TextView>(R.id.result)
        val context=this
        var db=DataBaseHandler(context)

        val getdeletebtn=findViewById<Button>(R.id.deletebtn)
        val getupdatebtn=findViewById<Button>(R.id.updatebtn)

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


        getreadbtn.setOnClickListener({
                    var data=db.readdata()
                    getreadddetailsview.text=""
            for(i in 0..(data.size-1)){
                getreadddetailsview.append(data.get(i).id.toString()+" "+data.get(i).taskname+" "+data.get(i).taskdes+" "+data.get(i).date+" "+data.get(i).category+" "+"\n")
            }
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        })

        getupdatebtn.setOnClickListener {
            if (taskname.text.isNotBlank() && taskdescription.text.isNotBlank() &&
                date.text.isNotBlank() && category.text.isNotBlank()
            ) {
                val taskId = // Get the task ID from wherever it's stored or passed
                    if (taskId != null) {
                        val task = Task(
                            taskId,
                            taskname.text.toString(),
                            taskdescription.text.toString(),
                            date.text.toString(),
                            category.text.toString()
                        )
                        db.updatedata(task)
                        getreadbtn.performClick()
                    } else {
                        Toast.makeText(context, "Task ID is null", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "Please fill all data", Toast.LENGTH_SHORT).show()
            }
        }




        getdeletebtn.setOnClickListener({
            db.deleteData()
            getreadbtn.performClick()
        })
    }


}