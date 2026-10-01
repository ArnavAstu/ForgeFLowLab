//package com.example.forgeflowlab.service;
//
//import com.example.forgeflowlab.dto.TaskRequestDTO;
//import com.example.forgeflowlab.entity.Task;
//import com.example.forgeflowlab.repository.TaskRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//
//    private final TaskRepository taskRepository;
//
//    public TaskService(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }
//
//    public String createTask(TaskRequestDTO requestDTO){
//        Task task = new Task();
//
//    }
//
//    public List<Task> getAllTasks(){
//        return taskRepository.findAll();
//    }
//
//    public Task getTaskById(Long id){
//        return taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
//    }
//
//    public Task updateTask(Long id, Task updatedTask){
//        Task task = taskRepository.findById(id)
//                .orElseThrow(()-> new RuntimeException("Task not found"));
//
//        task.setTitle(updatedTask.getTitle());
//        task.setPriority(updatedTask.getPriority());
//
//        return taskRepository.save(task);
//    }
//
//    public void deleteById(Long id){
//        taskRepository.deleteById(id);
//    }
//
//}

package com.example.forgeflowlab.service;

import com.example.forgeflowlab.dto.TaskRequest;
import com.example.forgeflowlab.dto.TaskResponse;
import com.example.forgeflowlab.entity.Task;
import com.example.forgeflowlab.exception.TaskNotFoundException;
import com.example.forgeflowlab.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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

    // TaskRequest DTO → Service → Task Entity → Repository → Database
    public String createTask(TaskRequest taskRequest) {

        Task task = new Task();

        // DTO → Entity
        task.setTitle(taskRequest.getTitle());
        task.setPriority(taskRequest.getPriority());

        taskRepository.save(task);

        return "Task is created";
    }


    /*
     * CURRENTLY:
     *
     * Database → Repository → Task Entity → Controller → Client
     *
     * Later:
     * Database → Repository → Task Entity
     * → TaskResponse DTO → Controller → Client
     */

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
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

    // Task Entity → TaskResponse DTO → Controller → Client
    public TaskResponse findById(Long id) {

//        Task task = taskRepository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException(
//                                "Task is not found with id: " + id
//                        )
//                );

        // using own created exception
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task is not found with id: " + id
                        )
                );

        // Entity → Response DTO
        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setPriority(task.getPriority());

        return taskResponse;
    }


    /*
     * DELETE FLOW:
     *
     * Client
     *   ↓
     * Controller
     *   ↓
     * Service
     *   ↓
     * Repository
     *   ↓
     * Database
     */

    public String deleteById(Long id) {

        taskRepository.deleteById(id);

        return "Task with id " + id + " is deleted";
    }


    /*
     * UPDATE FLOW:
     *
     * Client
     *   ↓ JSON
     * Controller
     *   ↓
     * Service
     *   ↓
     * Find existing Entity
     *   ↓
     * Update Entity
     *   ↓
     * Repository.save()
     *   ↓
     * Database
     */

    public String updateTask(Long id, Task updatedTask) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Task not found with id: " + id
                        )
                );

        task.setPriority(updatedTask.getPriority());
        task.setTitle(updatedTask.getTitle());

        taskRepository.save(task);

        return "Task with id: " + id + " is updated";
    }


    // derived queries

    public List<Task> findByPriorityAndTitleContainingIgnoreCase(String priority, String title){
        return taskRepository.findByPriorityAndTitleContainingIgnoreCase(priority, title);
    }

    // Sorting

    public List<Task> getTasksSortedByTitle(){
        return taskRepository.findAll(
                Sort.by("title").ascending() // Sorts title ascending (A-Z)
        );
    }

    public List<Task> getTasksSortedByPriority(){
        return taskRepository.findAll(
                Sort.by("priority").descending() // Sorts priority descending (Z-A)
        );
    }

    public List<Task> getSortedTasks(){
        Sort sort = Sort.by(
                Sort.Order.asc("priority"),  //first priority ascending if same priority then title descending
                Sort.Order.desc("title")
        );
        return taskRepository.findAll(sort);
    }

    // Pagination
    // means getting data in smaller chunks instead of returning everything at once.

    public Page<Task> getTasks(Pageable pageable){
        return taskRepository.findAll(pageable);
    }


    // Query + JPQL
    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.findTasksByPriority(priority);
    }

    public List<Task> searchTasks(
            String priority,
            String title) {

        return taskRepository.searchTasks(priority, title);
    }


}


