package org.teamnescafe.jtbpdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teamnescafe.jtbpdemo.entity.Homework;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
    List<Homework> findAllByActiveTrue();

    List<Homework> findAllByDescriptionIsNotNull();
}
