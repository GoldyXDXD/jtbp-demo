package org.teamnescafe.jtbpdemo.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
    List<Homework> findAllByActiveTrue();

    List<Homework> findAllByDescriptionIsNotNull();
}
