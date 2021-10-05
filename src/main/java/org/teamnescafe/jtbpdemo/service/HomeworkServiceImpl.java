package org.teamnescafe.jtbpdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.teamnescafe.jtbpdemo.entity.Homework;
import org.teamnescafe.jtbpdemo.entity.HomeworkRepository;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public void save(Homework homework) {

    }

    @Override
    public List<Homework> retrieveAllActiveHomework() {
        return homeworkRepository.findAllByActiveTrue();
    }

    @Override
    public List<Homework> retrieveAllHomework() {
        return homeworkRepository.findAllByDescriptionIsNotNull();
    }
}
