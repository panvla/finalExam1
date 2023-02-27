package com.vladimirpandurov.finalExam1.support;

import com.vladimirpandurov.finalExam1.domain.State;
import com.vladimirpandurov.finalExam1.dto.StateDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StateToStateDTO implements Converter<State, StateDTO> {


    @Override
    public StateDTO convert(State state) {
        if(state == null){
            return null;
        }

        StateDTO dto = new StateDTO();

        dto.setId(state.getId());
        dto.setName(state.getName());

        return dto;
    }

    public List<StateDTO> convert(List<State> stateList){
        List<StateDTO> dtoList = new ArrayList<>();
        for(State state : stateList){
            dtoList.add(convert(state));
        }
        return dtoList;
    }
}
