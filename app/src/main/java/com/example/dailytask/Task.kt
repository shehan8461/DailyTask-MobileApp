package com.example.dailytask

data class Task(
    var id: Int = 0,
    var taskname: String = "",
    var taskdes: String = "",
    var date: String = "",
    var category: String = ""
) {
    // Secondary constructor to initialize Task without an ID
    constructor(taskname: String, taskdes: String, date: String, category: String) : this() {
        this.taskname = taskname
        this.taskdes = taskdes
        this.date = date
        this.category = category
    }
}
