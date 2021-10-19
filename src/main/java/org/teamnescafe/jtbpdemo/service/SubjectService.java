package org.teamnescafe.jtbpdemo.service;

import org.teamnescafe.jtbpdemo.entity.Subject;

import java.util.List;

public interface SubjectService {

    void save(Subject subject);

    List<Subject> retrieveAllByDate(String date);

    List<Subject> retrieveAll();
}
