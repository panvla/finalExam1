package com.vladimirpandurov.finalExam1.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "state", cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();


    public void deleteTask(Task task){
        this.tasks.remove(task);
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }


}
