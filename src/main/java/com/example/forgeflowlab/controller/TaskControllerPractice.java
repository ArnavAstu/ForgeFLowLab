//package com.example.forgeflowlab.controller;
//
//import com.example.forgeflowlab.entity.Task;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class TaskControllerPractice {
//
//    @PostMapping("/tasks")
////    @PostMapping Maps an HTTP POST request to a specific controller method
//    public String createTask(@RequestBody Task task){
//        //   @RequestBody Tells Spring to convert incoming JSON → Java object
//        return task.getTitle();
//    }
//
//    @GetMapping("/tasks")
//    public List<Task> getTasks(@RequestParam String priority){
//        //@RequestParam → data from URL query
//        List<Task> lst = new ArrayList<>();
//        Task task1 = new Task();
//        Task task2 = new Task();
//
//        task1.setTitle("Learn Spring");
//        task1.setPriority("HIGH");
//
//        task2.setTitle("Practice DSA");
//        task2.setPriority("MEDIUM");
//
//        lst.add(task1);
//        lst.add(task2);
//
//        if (task1.getPriority().equals(priority)) {
//            lst.add(task1);
//        }
//        if (task2.getPriority().equals(priority)) {
//            lst.add(task2);
//        }
//
//        return lst;
//    }
//
//    @PostMapping("/tasks")
//    public ResponseEntity<String> createTaskk(@RequestBody Task task){
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body("Task created: "+ task.getTitle());
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    2nd DAY (doing 1st day practice)
//
//    @GetMapping("/tasks/{id}")
//    public String getTask(@PathVariable String id){
//        return "Task Id: " + id;
//    }
//
//    @GetMapping("/tasks")
//    public String getTaskk(@RequestParam String priority){
//        Task task = new Task();
//        task.setTitle("hello");
//        task.setPriority("High");
//
//        if (task.getPriority().equals(priority)){
//            return "Filtering tasks with priority: " + priority;
//        }
//        return "No task is there with the given priority";
//    }
//
//    @PostMapping("/tasks")
//    public String createTaskkk(@RequestBody Task task){
//        return "Task received" + task.getTitle();
//    }
//
//    @PostMapping("/tasks")
//    public ResponseEntity<String> createTaskkkk(@RequestBody Task task){
//        return ResponseEntity
//                .status(HttpStatus.CREATED) // forgot the status
//                .body("Task created: Build ForgeFlow");
//    }
//
//    @GetMapping("/tasks")
//    public List<Task> getTasks(){
//        List<Task> lst = new ArrayList<>();
//        Task task = new Task();
//        task.setTitle("hello");
//        task.setPriority("High");
//
//        lst.add(task);
//
//        return lst;
//    }
//
//
//
//
//}
