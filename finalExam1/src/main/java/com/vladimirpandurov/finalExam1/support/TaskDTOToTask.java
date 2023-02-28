package com.vladimirpandurov.finalExam1.support;

import com.vladimirpandurov.finalExam1.domain.Sprint;
import com.vladimirpandurov.finalExam1.domain.State;
import com.vladimirpandurov.finalExam1.domain.Task;
import com.vladimirpandurov.finalExam1.dto.TaskDTO;
import com.vladimirpandurov.finalExam1.service.SprintService;
import com.vladimirpandurov.finalExam1.service.StateService;
import com.vladimirpandurov.finalExam1.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskDTOToTask implements Converter<TaskDTO, Task> {

    private final TaskService taskService;
    private final StateService stateService;
    private final SprintService sprintService;

    @Override
    public Task convert(TaskDTO dto) {
        Task task = new Task();
        List<State> states = stateService.findAll();
        int stateId = Math.toIntExact(dto.getStateId());
        if(stateId>= states.size()){
             stateId = states.size();
        }
        State state = stateService.findOne((long) stateId);
        log.info("uzimanje State " + state);
        Sprint sprint = sprintService.findOne(dto.getSprintId());
        log.info("uzimanje Sprinta " + sprint);
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setSubscriber(dto.getSubscriber());
        task.setPoints(dto.getPoints());
        task.setSprint(sprint);
        task.setState(state);
        log.info("da vidimo kako izgleda task " + task);
        return task;
    }

    public List<Task> convert(List<TaskDTO>dtoList){
        List<Task> taskList = new ArrayList<>();
        for(TaskDTO dto : dtoList){
            taskList.add(convert(dto));
        }
        return taskList;
    }
}
