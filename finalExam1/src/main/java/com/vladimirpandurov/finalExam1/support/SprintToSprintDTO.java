package com.vladimirpandurov.finalExam1.support;

import com.vladimirpandurov.finalExam1.domain.Sprint;
import com.vladimirpandurov.finalExam1.dto.SprintDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SprintToSprintDTO implements Converter<Sprint, SprintDTO> {
    @Override
    public SprintDTO convert(Sprint sprint) {
        if(sprint == null){
            return null;
        }
        SprintDTO dto = new SprintDTO();
        dto.setId(sprint.getId());
        dto.setName(sprint.getName());
        dto.setPoints(sprint.getPoints());

        return dto;
    }

    public List<SprintDTO> convert (List<Sprint> sprintList){
        List<SprintDTO> dtoList = new ArrayList<>();
        for(Sprint sprint: sprintList){
            dtoList.add(convert(sprint));
        }
        return dtoList;
    }
}
