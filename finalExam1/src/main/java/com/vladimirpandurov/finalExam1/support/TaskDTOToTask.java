package com.vladimirpandurov.finalExam1.support;

import com.vladimirpandurov.finalExam1.domain.Sprint;
import com.vladimirpandurov.finalExam1.domain.State;
import com.vladimirpandurov.finalExam1.domain.Task;
import com.vladimirpandurov.finalExam1.dto.TaskDTO;
import com.vladimirpandurov.finalExam1.service.SprintService;
import com.vladimirpandurov.finalExam1.service.StateService;
import com.vladimirpandurov.finalExam1.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskDTOToTask implements Converter<TaskDTO, Task> {

    private final TaskService taskService;
    private final StateService stateService;
    private final SprintService sprintService;

    @Override
    public Task convert(TaskDTO dto) {
        Task task = new Task();
        State state = stateService.findOne(dto.getStateId());
        Sprint sprint = sprintService.findOne(dto.getSprintId());

        task.setName(dto.getName());
        task.setSubscriber(dto.getSubscriber());
        task.setPoints(dto.getPoints());
        task.setSprint(sprint);
        task.setState(state);
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
