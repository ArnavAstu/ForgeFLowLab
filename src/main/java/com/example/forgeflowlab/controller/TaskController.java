//package com.example.forgeflowlab.controller;
//
//import com.example.forgeflowlab.dto.TaskRequestDTO;
//import com.example.forgeflowlab.entity.Task;
//import com.example.forgeflowlab.service.TaskService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class TaskController {
//
//
//    private final TaskService taskService;
//
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    // should not directly communicate to entity layer
////    @PostMapping("/tasks")
////    public String createTask(@RequestBody Task task){
////        return taskService.createTask(task);
////    }
//
//    //dto layer should be in between controller and entity layer
//    @PostMapping("/tasks")
//    public String createTask(@RequestBody TaskRequestDTO requestDTO){
//        return taskService.createTask(requestDTO);
//    }
//
//
//    @GetMapping("/tasks")
//    public List<Task> showAllTasks(){
//        return taskService.getAllTasks();
//    }
//
//    @GetMapping("/tasks/{id}")
//    public Task getTaskById(@PathVariable Long id){
//        return taskService.getTaskById(id);
//    }
//
//    @PutMapping("/tasks/{id}")
//    public Task updateTasks(@PathVariable Long id, @RequestBody Task updatedTask){
//        return taskService.updateTask(id,updatedTask);
//    }
//
//    @DeleteMapping("/tasks/{id}")
//    public String deleteById(@PathVariable Long id){
//        taskService.deleteById(id);
//        return "Task deleted with id: " + id;
//    }
//
//}

package com.example.forgeflowlab.controller;

import com.example.forgeflowlab.dto.TaskRequest;
import com.example.forgeflowlab.dto.TaskResponse;
import com.example.forgeflowlab.entity.Task;
import com.example.forgeflowlab.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /*
     * REQUEST FLOW:
     *
     * Client
     *   ↓ JSON
     * TaskRequest DTO
     *   ↓
     * Controller
     *   ↓
     * Service
     *   ↓ DTO → Entity
     * Task Entity
     *   ↓
     * Repository
     *   ↓
     * Database
     */

    // Client → TaskRequest DTO → Controller → Service
    @PostMapping("/tasks")
    public String createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }


    /*
     * RESPONSE FLOW:
     *
     * Database
     *   ↓
     * Repository
     *   ↓
     * Task Entity
     *   ↓ Entity → DTO
     * Service
     *   ↓
     * TaskResponse DTO
     *   ↓
     * Controller
     *   ↓ JSON
     * Client
     */

    @GetMapping("/tasks/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return taskService.findById(id);
    }


    // Returns all Task entities → Controller → Client
    // Later we can change this to List<TaskResponse> using DTOs.
    @GetMapping("/tasks")
    public List<Task> getTask() {
        return taskService.getAllTasks();
    }


    // Client → Task Entity → Controller → Service
    // Later we will change this to TaskRequest DTO.
    @PutMapping("/tasks/{id}")
    public String updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask) {

        return taskService.updateTask(id, updatedTask);
    }


    // Client → Controller → Service → Repository → Database
    @DeleteMapping("/tasks/{id}")
    public String deleteById(@PathVariable Long id) {
        return taskService.deleteById(id);
    }

    // derived queries
    @GetMapping("/tasks")
    public List<Task> findByPriorityAndTitleContainingIgnoreCase(@RequestParam  String priority, @RequestParam String title){
        return taskService.findByPriorityAndTitleContainingIgnoreCase(priority, title);
    }

    //Sorting
    @GetMapping("/tasks/sort")
    public List<Task> getSortedTasks(){
        return taskService.getSortedTasks();
    }

    //Pagination
    @GetMapping("/tasks")
    public Page<Task> getTasks(Pageable pageable){
        return taskService.getTasks(pageable);
    }

    //@Query + JPQL
    @GetMapping("/tasks/priority")
    public List<Task> getTasksByPriority(
            @RequestParam String priority) {

        return taskService.getTasksByPriority(priority);
    }

    @GetMapping("/tasks/search")
    public List<Task> searchTasks(
            @RequestParam String priority,
            @RequestParam String title) {

        return taskService.searchTasks(priority, title);
    }




}