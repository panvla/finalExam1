package com.vladimirpandurov.finalExam1.service;

import com.vladimirpandurov.finalExam1.domain.State;
import com.vladimirpandurov.finalExam1.exception.StateNotFoundException;
import com.vladimirpandurov.finalExam1.repository.StateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StateService {

    private final StateRepository stateRepository;

    public State findOne(Long id){
        return this.stateRepository.findById(id).orElseThrow(()-> new StateNotFoundException("State with id " + id + " not found!"));
    }

    public List<State> findAll(){
        return this.stateRepository.findAll();
    }

    public State save(State state){
        return this.stateRepository.save(state);
    }

    public void delete(Long id){
        State state = findOne(id);
        if(state != null){
            this.stateRepository.delete(state);
        }
    }

}
