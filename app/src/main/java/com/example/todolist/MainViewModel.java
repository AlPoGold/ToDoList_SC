package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private TaskDataBase taskDataBase;
    int count=0;
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();
    public MainViewModel(@NonNull Application application) {
        super(application);
        taskDataBase = TaskDataBase.getInstance(application);
    }

    public LiveData<List<Task>> getNotes(){
        return taskDataBase.tasksDao().getTasks();
    }

    public void showCount(){
        count++;
        countLiveData.setValue(count);
    }

    public LiveData<Integer> getCount(){
        return countLiveData;
    }

    public void remove(Task task){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                taskDataBase.tasksDao().removeTask(task.getId());
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                showTasks();
//                            }
//                        });
            }
        });
        thread.start();


    }
}
