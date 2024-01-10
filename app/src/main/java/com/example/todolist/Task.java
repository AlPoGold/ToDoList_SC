package com.example.todolist;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private int priority;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }


    @Ignore
    public Task(String text, int priority) {
        this(0, text, priority);
    }

    public Task(int id, String text, int priority) {
        this.id = id;
        this.text = text;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id:" + id +
                ", text:'" + text  +
                ", priority:" + priority +
                '}';
    }
}
