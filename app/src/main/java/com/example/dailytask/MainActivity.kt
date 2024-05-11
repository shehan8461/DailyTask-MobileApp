package com.example.dailytask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getstartbtn=findViewById<Button>(R.id.start_btn)

        val getreaddbtn=findViewById<Button>(R.id.taskbtn)
        val getreadddetailsview=findViewById<TextView>(R.id.results)
        val context=this
        var db=DataBaseHandler(context)


        getstartbtn.setOnClickListener {
            startActivity(Intent(this,TaskViews::class.java))
        }





        getreaddbtn.setOnClickListener({
            var data=db.readdata()
            getreadddetailsview.text=""
            for(i in 0..(data.size-1)){
                getreadddetailsview.append(data.get(i).id.toString()+  "       "+data.get(i).taskname+"         "+data.get(i).taskdes+"        "+data.get(i).date+"          "+data.get(i).category+"                "+"\n\n")
            }
        })


    }
}