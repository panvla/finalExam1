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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
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
            Integer totalPoints = 0;
            for(Task taskAdd : sprint.getTasks()){
                totalPoints += taskAdd.getPoints();
            }
            sprint.setPoints(totalPoints);
            this.sprintRepository.save(sprint);


        }
        return savedTask;
    }


    public Task update(Task task){
        Task existingTask = taskRepository.findById(task.getId()).orElse(null);
        if(existingTask != null){
            existingTask.setName(task.getName());
            existingTask.setSubscriber(task.getSubscriber());
            existingTask.setPoints(task.getPoints());
            existingTask.setState(task.getState());
            existingTask.setSprint(task.getSprint());
            Task savedTask = taskRepository.save(existingTask);
//            Sprint sprint = savedTask.getSprint();
//            Integer totalPoints = 0;
//            for(Task taskAdd : sprint.getTasks()){
//                totalPoints += taskAdd.getPoints();
//            }
//            sprint.setPoints(totalPoints);
//            this.sprintRepository.save(sprint);
            return savedTask;
        }
        return task;
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
