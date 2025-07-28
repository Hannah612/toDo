package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.entities.Task;
import org.example.mappers.TaskMapper;
import org.example.mappers.UserMapper;
import org.example.repositories.TaskRepository;
import org.example.repositories.UserRepository;
import org.example.requests.TaskDeleteRequest;
import org.example.requests.TaskRequest;
import org.example.requests.UserRequest;
import org.example.types.SortType;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

//    @GetMapping()
//    public List<TaskRequest> getAllTasks(
//    ){
//        return taskRepository.findAll()
//                .stream()
//                .map(taskMapper::toDto)// same as user -> userMapper.toDto(user)
//                .toList();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskRequest> getTask(
            @PathVariable Long id
    ){
        var task = taskRepository.findById(id).orElse(null); //JPA repo
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(taskMapper.toDto(task)); //toDto changes Task to TaskRequest
    }

    public Sort.Direction findDirection(String order) {
        if (Objects.equals(order, "asc")) return Sort.Direction.ASC;
        return Sort.Direction.DESC;
    }

//    @GetMapping(value = "/search", params = "q")
//    public List<TaskRequest> searchForTask(
//            @RequestParam String q
//    ){
//        System.out.println(q);
//        System.out.println("q");
//        return taskRepository.findByQuery(q)
//                        .stream()
//                        .map(taskMapper::toDto)
//                        .toList();
//    }


    //e.g. GET ?query=hello&sort=completed&order=asc
    @GetMapping(params = "query, sort, order")
    public List<TaskRequest> getTasks(
            @RequestParam String query,
            @RequestParam String sort,
            @RequestParam String order
    ){
        List<Task> findBy = null;
        Sort.Direction direction = this.findDirection(order);
        try {

            SortType sortType = SortType.valueOf(type.toUpperCase());
            findBy = taskRepository.findByQueryAndSort(query);
            return findBy.stream()
                    .map(taskMapper::toDto)
                    .toList();
//
//            if (sortType == SortType.PRIORITY_ID){
//                findBy = taskRepository.findByPriorityId(sort);
//            } else if (sortType == SortType.COMPLETED) {
//                findBy = taskRepository.findByCompleted(sort);
//            } else {
//                findBy = taskRepository.findByDueDate(sort);
//            }

//            return findBy.stream()
//                    .map(taskMapper::toDto)
//                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unable to sort using the parameters given.");
        }

    }

    @PostMapping
    public ResponseEntity<TaskRequest> createTask(
        @RequestBody TaskRequest newTask,
        UriComponentsBuilder uriBuilder
    ) {
        System.out.println("newTask");
        System.out.println(newTask);

        var createdTask = taskMapper.toEntity(newTask);
        taskRepository.save(createdTask); //JPA repo update/create entity and generate id
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(newTask.getId()).toUri(); //return 201 created status
        return ResponseEntity.created(uri).body(newTask); //return status code 201 and the userDto as the body
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        var task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.delete(task);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete-multiple-tasks")
    public ResponseEntity<?> deleteMultipleTasks(@RequestBody List<TaskDeleteRequest> tasksToDelete) {
        try {
            for (TaskDeleteRequest task : tasksToDelete) {
                this.deleteTask(task.getTaskId());
            }
            return ResponseEntity.ok().build();
        } catch(Error e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deletion failed. Please try again later");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequest updatedContents,
            UriComponentsBuilder uriBuilder) {

            System.out.println("taskId to update");
            System.out.println(id);

        System.out.println("body");
        System.out.println(updatedContents);
        var existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask == null) {
            return ResponseEntity.notFound().build();
        }
        updatedContents.setId(id);
        var created = taskMapper.toEntity(updatedContents);
        taskRepository.save(created);
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(existingTask.getId()).toUri();
        return ResponseEntity.created(uri).body(existingTask);
    }

}
