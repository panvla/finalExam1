package com.vladimirpandurov.finalExam1.domain;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "subscriber")
    private String subscriber;

    @Column(name = "points")
    private Integer points;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "state_id")
    private State state;

    public void setSprint(Sprint sprint){
        this.sprint = sprint;
        sprint.addTask(this);

    }

    public void setState(State state){
        this.state = state;
        if(!state.getTasks().contains(this)){
            state.addTask(this);
        }
    }

}
