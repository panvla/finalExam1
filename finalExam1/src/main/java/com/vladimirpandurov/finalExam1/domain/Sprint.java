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
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "points")
    private Integer points;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public void deleteTask(Task task){
        this.tasks.remove(task);
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

}
