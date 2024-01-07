package com.example.todolist;

import java.util.ArrayList;
import java.util.Random;



public class DataBaseTasks {
    private ArrayList<Task> tasks = new ArrayList<>();
    private static DataBaseTasks instance = null;

    public static DataBaseTasks getInstance(){
        if(instance==null) {
            instance = new DataBaseTasks();
        }
        return instance;
    }


    public DataBaseTasks(){
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int rNum = random.nextInt(3);
            Task task = new Task(i, i+":Task", rNum);
            tasks.add(task);
        }
    }

    public void add(Task task){
        tasks.add(task);

    }

    public void remove(int id){
        for (int i = 0; i < tasks.size()-1; i++) {
            Task task = tasks.get(i);
            if(task.getId()==id){
                tasks.remove(task);
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task: tasks
             ) {
            sb.append(task);
            sb.append("\n");
        }
        return sb.toString();
    }
}
