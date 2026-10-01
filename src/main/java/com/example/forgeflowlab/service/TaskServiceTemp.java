package com.example.forgeflowlab.service;

import com.example.forgeflowlab.dto.TaskRequestTemp;
import com.example.forgeflowlab.dto.TaskResponseTemp;
import com.example.forgeflowlab.entity.Tasktemp;
import com.example.forgeflowlab.exception.TaskNotFoundException;
import com.example.forgeflowlab.repository.TaskRepositoryTemp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TaskServiceTemp {

    private final TaskRepositoryTemp taskRepositoryTemp;
    public TaskServiceTemp(TaskRepositoryTemp taskRepositoryTemp){
        this.taskRepositoryTemp = taskRepositoryTemp;
    }

    public String createTask(TaskRequestTemp taskRequestTemp){
        Tasktemp tasktemp = new Tasktemp();
        tasktemp.setTitle(taskRequestTemp.getTitle());
        tasktemp.setPriority(taskRequestTemp.getPriority());
        taskRepositoryTemp.save(tasktemp);

        return "New task is created with id: " + tasktemp.getId();
    }

    public List<Tasktemp> getAllTasks(){
        return taskRepositoryTemp.findAll();
    }

    public TaskResponseTemp getTaskById(Long Id){
        Tasktemp tasktemp = taskRepositoryTemp.findById(Id).orElseThrow(()->
                new TaskNotFoundException("Task with given id is not found"));
        TaskResponseTemp taskResponseTemp = new TaskResponseTemp();
        taskResponseTemp.setId(tasktemp.getId());
        taskResponseTemp.setPriority(tasktemp.getPriority());
        taskResponseTemp.setTitle(tasktemp.getTitle());

        return taskResponseTemp;
    }

    public String updateTaskWihId(Long Id, TaskRequestTemp taskRequestTemp){
        Tasktemp tasktemp = taskRepositoryTemp.findById(Id).orElseThrow(()->
                new TaskNotFoundException("Task with given Id is not found"));
        tasktemp.setPriority(taskRequestTemp.getPriority());
        tasktemp.setTitle(taskRequestTemp.getTitle());
        taskRepositoryTemp.save(tasktemp);
        return "The task with id: " + Id + " has been updated";
    }

    public String deleteTaskById(Long Id){
        Tasktemp tasktemp = taskRepositoryTemp.findById(Id).orElseThrow(()->
                new TaskNotFoundException("Task with given Id is not found"));
        taskRepositoryTemp.deleteById(Id);
        return "The task with given Id "+Id+ " is deleted";
    }

    public List<Tasktemp> findByPriorityAndTitleContainingIgnoreCase(String priority, String title){
        return taskRepositoryTemp.findByPriorityAndTitleContainingIgnoreCase(priority, title);
    }

}
