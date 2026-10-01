package com.example.forgeflowlab.controller;

import com.example.forgeflowlab.dto.TaskRequest;
import com.example.forgeflowlab.dto.TaskRequestTemp;
import com.example.forgeflowlab.dto.TaskResponse;
import com.example.forgeflowlab.dto.TaskResponseTemp;
import com.example.forgeflowlab.entity.Tasktemp;
import com.example.forgeflowlab.service.TaskServiceTemp;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskControllerTemp {

    private final TaskServiceTemp taskServiceTemp;
    public TaskControllerTemp(TaskServiceTemp taskServiceTemp){
        this.taskServiceTemp = taskServiceTemp;
    }

    @PostMapping("/tasks")
    public String createTask(@Valid @RequestBody TaskRequestTemp taskRequestTemp){
        return taskServiceTemp.createTask(taskRequestTemp);
    }

    @GetMapping("/tasks")
    public List<Tasktemp> getAllTasks(){
        return taskServiceTemp.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskResponseTemp getTaskWithId(@PathVariable Long Id){
        return taskServiceTemp.getTaskById(Id);
    }

    @PutMapping("/tasks/{id}")
    public String updateTaskWithId(@PathVariable Long Id, @RequestBody TaskRequestTemp taskRequestTemp){
        return taskServiceTemp.updateTaskWihId(Id,taskRequestTemp);
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTaskById(@PathVariable Long Id){
        return taskServiceTemp.deleteTaskById(Id);
    }

    @GetMapping("/tasks/search")
    public List<Tasktemp> findByPriorityAndTitleContainingIgnoreCase(@RequestParam String priority, @RequestParam String title){
        return taskServiceTemp.findByPriorityAndTitleContainingIgnoreCase(priority, title);
    }

}
