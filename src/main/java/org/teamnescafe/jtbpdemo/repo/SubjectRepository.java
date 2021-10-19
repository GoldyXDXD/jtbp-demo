package org.teamnescafe.jtbpdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.teamnescafe.jtbpdemo.entity.Subject;

import java.sql.Date;
import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findAll();

    List<Subject> findAllByDate(Date date);
}
