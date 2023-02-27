package com.vladimirpandurov.finalExam1.controller;

import com.vladimirpandurov.finalExam1.domain.State;
import com.vladimirpandurov.finalExam1.dto.StateDTO;
import com.vladimirpandurov.finalExam1.service.StateService;
import com.vladimirpandurov.finalExam1.support.StateToStateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/states")
@RequiredArgsConstructor
public class ApiStateController {

    private final StateService stateService;
    private final StateToStateDTO stateToStateDTO;

    @GetMapping
    public ResponseEntity<List<StateDTO>> getAll(){
        List<State> stateList = this.stateService.findAll();
        return new ResponseEntity<>(this.stateToStateDTO.convert(stateList), HttpStatus.OK);
    }

}
