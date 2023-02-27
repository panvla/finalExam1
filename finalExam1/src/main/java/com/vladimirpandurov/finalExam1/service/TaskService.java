package com.vladimirpandurov.finalExam1.service;

import com.vladimirpandurov.finalExam1.domain.Sprint;
import com.vladimirpandurov.finalExam1.domain.State;
import com.vladimirpandurov.finalExam1.domain.Task;
import com.vladimirpandurov.finalExam1.exception.TaskNotFoundException;
import com.vladimirpandurov.finalExam1.repository.SprintRepository;
import com.vladimirpandurov.finalExam1.repository.StateRepository;
import com.vladimirpandurov.finalExam1.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;

    public Task findOne(Long id){
        return this.taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task with id " + id + " not found"));
    }

    public Page<Task> findAll(int pageNum){
        return this.taskRepository.findAll(PageRequest.of(pageNum,10));
    }

    public Task save(Task task){
        Task savedTask = this.taskRepository.save(task);
        if(savedTask != null){
            Sprint sprint = savedTask.getSprint();
            sprint.setPoints(sprint.getPoints() + savedTask.getPoints());
            this.sprintRepository.save(sprint);
        }
        return savedTask;
    }

    public void delete(Long id){
        Task task = findOne(id);
        if(task != null) {
            Sprint sprint = task.getSprint();
            sprint.setPoints(sprint.getPoints() - task.getPoints());
            sprint.deleteTask(task);
            State state = task.getState();
            state.deleteTask(task);
            this.taskRepository.delete(task);
        }
    }







}
