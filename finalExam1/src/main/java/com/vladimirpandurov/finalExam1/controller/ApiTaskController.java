package com.vladimirpandurov.finalExam1.controller;

import com.vladimirpandurov.finalExam1.domain.Task;
import com.vladimirpandurov.finalExam1.dto.TaskDTO;
import com.vladimirpandurov.finalExam1.service.TaskService;
import com.vladimirpandurov.finalExam1.support.TaskDTOToTask;
import com.vladimirpandurov.finalExam1.support.TaskToTaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class ApiTaskController {

    private final TaskService taskService;
    private final TaskToTaskDTO taskToTaskDTO;
    private final TaskDTOToTask taskDTOToTask;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll(@RequestParam(value="pageNum", defaultValue = "0") int pageNum){
        Page<Task> taskList = taskService.findAll(pageNum);
        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(taskList.getTotalPages()));
        return new ResponseEntity<>(taskToTaskDTO.convert(taskList.getContent()), headers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("id") Long id){
        Task task = taskService.findOne(id);

        return new ResponseEntity<>(this.taskToTaskDTO.convert(task), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<TaskDTO> add(@Validated @RequestBody TaskDTO taskDTO){
        Task savedTask = taskService.save(this.taskDTOToTask.convert(taskDTO));
        return new ResponseEntity<>(this.taskToTaskDTO.convert(savedTask), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@Validated @RequestBody TaskDTO taskDTO, @PathVariable Long id){
        if(!id.equals(taskDTO.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Task savedTask = this.taskService.save(this.taskDTOToTask.convert(taskDTO));
        return new ResponseEntity<>(this.taskToTaskDTO.convert(savedTask), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
