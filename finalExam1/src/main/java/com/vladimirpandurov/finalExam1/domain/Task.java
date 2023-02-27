package com.vladimirpandurov.finalExam1.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;


@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subscriber;
    private Integer points;
    @ManyToOne(fetch = FetchType.EAGER)
    private Sprint sprint;
    @ManyToOne(fetch = FetchType.EAGER)
    private State state;

    public void setSprint(Sprint sprint){
        this.sprint = sprint;
        if(!sprint.getTasks().contains(this)){
            sprint.addTask(this);
        }
    }

    public void setState(State state){
        this.state = state;
        if(!state.getTasks().contains(this)){
            state.addTask(this);
        }
    }

}
