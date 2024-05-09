package com.example.dailytask

class Task{
    var id:Int=0
    var taskname:String=""
    var taskdes:String=""
    var date:String=""
    var category:String=""

    constructor(taskname:String,taskdes:String,date:String,category:String){
        this.taskname=taskname
        this.taskdes=taskdes
        this.date=date
        this.category=category
    }
    constructor(){

    }


}