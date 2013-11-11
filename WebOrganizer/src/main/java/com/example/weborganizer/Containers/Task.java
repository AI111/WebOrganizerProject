package com.example.weborganizer.Containers;

/**
 * Created by Sasha on 10.11.13.
 */
public class Task {
public  String taskTitle;
public  String taskText;
public  String taskTime;
public  String taskDate;
public  String lastTaskLastEditing;
public  int taskFilterId;
public  byte taskEditType;
public  int userId;
public Task(String taskTitle,String taskText,String taskTime,String taskDate,String lastTaskLastEditing,byte taskEditType,
            int taskFilterId,int userId ){
    this.taskTitle=taskTitle;
    this.taskText=taskText;
    this.taskTime=taskTime;
    this.taskDate=taskDate;
    this.lastTaskLastEditing=lastTaskLastEditing;
    this.taskEditType=taskEditType;
    this.taskFilterId=taskFilterId;
    this.userId=userId;

}

    @Override
    public String toString() {
        return taskTitle+" "+taskText+"  "+taskTime+"  "+taskDate+" "+lastTaskLastEditing+"  "+
                taskEditType+" "+taskFilterId+"  "+userId;
    }
}
