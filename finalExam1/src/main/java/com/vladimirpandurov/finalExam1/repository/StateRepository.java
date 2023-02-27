package com.vladimirpandurov.finalExam1.repository;

import com.vladimirpandurov.finalExam1.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
