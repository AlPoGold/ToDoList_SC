package com.example.todolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao

public interface TaskDao {
    @Query("SELECT * FROM tasks")
    Single<List<Task>> getTasks();
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    Completable addTask(Task task);

    @Query("DELETE FROM tasks WHERE id=:id")
    Completable removeTask(int id);
}
