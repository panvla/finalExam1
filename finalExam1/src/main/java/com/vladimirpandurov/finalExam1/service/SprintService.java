package com.vladimirpandurov.finalExam1.service;

import com.vladimirpandurov.finalExam1.domain.Sprint;
import com.vladimirpandurov.finalExam1.exception.SprintNotFoundException;
import com.vladimirpandurov.finalExam1.repository.SprintRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SprintService {

    private final SprintRepository sprintRepository;

    public Sprint findOne(Long id){
        return sprintRepository.findById(id).orElseThrow(()->new SprintNotFoundException("Sprint with id " + id + " not found"));
    }

    public List<Sprint> findAll(){
        return sprintRepository.findAll();
    }

    public Sprint save(Sprint sprint){
        return sprintRepository.save(sprint);
    }

    public void delete(Long id){
        Sprint sprint = findOne(id);
        if(sprint!= null){
            sprintRepository.delete(sprint);
        }
    }

}
