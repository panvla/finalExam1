package com.vladimirpandurov.finalExam1;

import com.vladimirpandurov.finalExam1.domain.Sprint;
import com.vladimirpandurov.finalExam1.domain.State;
import com.vladimirpandurov.finalExam1.domain.Task;
import com.vladimirpandurov.finalExam1.service.SprintService;
import com.vladimirpandurov.finalExam1.service.StateService;
import com.vladimirpandurov.finalExam1.service.TaskService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestData {

    private final SprintService sprintService;
    private final StateService stateService;
    private final TaskService taskService;

    @PostConstruct
    public void init(){
        Sprint sprint = new Sprint();
        sprint.setName("FirstSprint");
        sprint.setPoints(0);
        this.sprintService.save(sprint);
        sprint = new Sprint();
        sprint.setName("SecondSprint");
        sprint.setPoints(0);
        this.sprintService.save(sprint);
        sprint = new Sprint();
        sprint.setName("ThirdSprint");
        sprint.setPoints(0);
        this.sprintService.save(sprint);

        State state = new State();
        state.setName("Start");
        this.stateService.save(state);
        state = new State();
        state.setName("25% Done");
        this.stateService.save(state);
        state = new State();
        state.setName("50% Done");
        this.stateService.save(state);
        state = new State();
        state.setName("75% Done");
        this.stateService.save(state);
        state = new State();
        state.setName("Finished");
        this.stateService.save(state);

        Task task = new Task();
        task.setName("Create Project");
        task.setSubscriber("Peter Schift");
        task.setPoints(5);
        task.setState(stateService.findOne(1L));
        task.setSprint(sprintService.findOne(1L));
        taskService.save(task);

        task = new Task();
        task.setName("Update Project");
        task.setSubscriber("Duglas Schmith");
        task.setPoints(10);
        task.setState(stateService.findOne(2L));
        task.setSprint(sprintService.findOne(1L));
        taskService.save(task);

        task = new Task();
        task.setName("Add some stuff");
        task.setSubscriber("John Doe");
        task.setPoints(15);
        task.setState(stateService.findOne(3L));
        task.setSprint(sprintService.findOne(1L));
        taskService.save(task);

    }

}
