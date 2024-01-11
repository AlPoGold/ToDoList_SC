package com.example.todolist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private TaskDataBase taskDataBase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable disposable;
    int count=0;
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Task>> tasks = new MutableLiveData<>();
    public MainViewModel(@NonNull Application application) {
        super(application);
        taskDataBase = TaskDataBase.getInstance(application);
    }

    public LiveData<List<Task>> getNotes(){

        return tasks;
    }

    public void refreshList(){
        Disposable disposable1 = taskDataBase.tasksDao().getTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasksFROMdb) throws Throwable {
                            tasks.setValue(tasksFROMdb);
                    }
                });
    }

    public void showCount(){
        count++;
        countLiveData.setValue(count);
    }

    public LiveData<Integer> getCount(){
        return countLiveData;
    }

    public void remove(Task task){
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                taskDataBase.tasksDao().removeTask(task.getId());
////                        handler.post(new Runnable() {
////                            @Override
////                            public void run() {
////                                showTasks();
////                            }
////                        });
//            }
//        });
//        thread.start();
        disposable = taskDataBase.tasksDao().removeTask(task.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("delete_task", "task was deleted");
                        refreshList();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
