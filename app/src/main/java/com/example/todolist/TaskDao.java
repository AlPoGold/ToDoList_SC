package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao

public interface TaskDao {
    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getTasks();
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    void addTask(Task task);

    @Query("DELETE FROM tasks WHERE id=:id")
    void removeTask(int id);
}
