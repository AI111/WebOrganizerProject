package com.example.weborganizer.Containers;

/**
 * Created by Sasha on 10.11.13.
 */
public class Task1 {
public final String taskTitle;
public final String taskText;
public final String taskTime;
public final String taskDate;
public final String lastTaskLastEditing;
public final byte taskEditType;
public final int taskFilterId;
public final String taskFilter;
public final int userId;
public Task1(String taskTitle, String taskText, String taskTime, String taskDate, String lastTaskLastEditing, byte taskEditType,
             int taskFilterId, String taskFilter, int userId){
    this.taskTitle=taskTitle;
    this.taskText=taskText;
    this.taskTime=taskTime;
    this.taskDate=taskDate;
    this.lastTaskLastEditing=lastTaskLastEditing;
    this.taskEditType=taskEditType;
    this.taskFilterId=taskFilterId;
    this.taskFilter=taskFilter;
    this.userId=userId;

}

    @Override
    public String toString() {
        return taskTitle+"\n "+taskText+"\n "+taskTime+"\n "+taskDate+"\n "+lastTaskLastEditing+"\n "
                +taskEditType+"\n "+taskFilterId+"\n"+taskFilter+"\n "+userId;
    }
}
