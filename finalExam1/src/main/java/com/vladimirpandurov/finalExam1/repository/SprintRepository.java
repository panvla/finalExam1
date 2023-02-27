package com.vladimirpandurov.finalExam1.repository;

import com.vladimirpandurov.finalExam1.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
