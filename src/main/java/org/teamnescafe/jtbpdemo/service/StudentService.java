package org.teamnescafe.jtbpdemo.service;

import org.teamnescafe.jtbpdemo.entity.Student;

import java.util.List;

public interface StudentService {

    void save(Student student);

    List<Student> retrieveAll();
}
