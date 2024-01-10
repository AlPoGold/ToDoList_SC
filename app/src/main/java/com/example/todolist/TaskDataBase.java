package com.example.todolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Task.class}, version = 1)
public abstract class TaskDataBase extends RoomDatabase {

    public static TaskDataBase instance = null;
    private static final String DB_name = "tasks.db";

    public static TaskDataBase getInstance(Application application){
        if(instance==null){
            instance = Room.databaseBuilder(
                    application,
                    TaskDataBase.class,
                    DB_name
            ).allowMainThreadQueries()
                    .build();

        }
        return instance;
    }

    public abstract TaskDao tasksDao();

}
