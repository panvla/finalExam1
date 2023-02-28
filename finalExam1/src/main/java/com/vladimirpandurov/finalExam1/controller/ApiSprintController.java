package com.vladimirpandurov.finalExam1.controller;

import com.vladimirpandurov.finalExam1.domain.Sprint;
import com.vladimirpandurov.finalExam1.dto.SprintDTO;
import com.vladimirpandurov.finalExam1.service.SprintService;
import com.vladimirpandurov.finalExam1.support.SprintToSprintDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sprints")
@RequiredArgsConstructor
@CrossOrigin
public class ApiSprintController {

    private final SprintService sprintService;
    private final SprintToSprintDTO sprintToSprintDTO;

    @GetMapping
    public ResponseEntity<List<SprintDTO>> getAll(){

        List<Sprint> sprintList = this.sprintService.findAll();
        return new ResponseEntity<>(this.sprintToSprintDTO.convert(sprintList), HttpStatus.OK);

    }

}
