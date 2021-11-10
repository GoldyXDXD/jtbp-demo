package org.teamnescafe.jtbpdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamnescafe.jtbpdemo.entity.Subject;
import org.teamnescafe.jtbpdemo.repo.SubjectRepository;

import java.sql.Date;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public List<Subject> retrieveAll() {
        return subjectRepository.findAll();
    }

    public List<Subject> retrieveAllByDate(String date) {
        return subjectRepository.findAllByDate(Date.valueOf(date));
    }
}
