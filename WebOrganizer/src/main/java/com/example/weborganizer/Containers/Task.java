package com.example.weborganizer.Containers;

/**
 * Created by Sasha on 10.11.13.
 */
public class Task {
public final String taskTitle;
public final String taskText;
public final String taskTime;
public final String taskDate;
public final String lastTaskLastEditing;
public final int taskFilterId;
public final byte taskEditType;
public final int userId;
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
        return taskTitle+"\n "+taskText+"\n "+taskTime+"\n "+taskDate+"\n "+lastTaskLastEditing+"\n "+
                taskEditType+"\n"+taskFilterId+"\n "+userId;
    }
}
