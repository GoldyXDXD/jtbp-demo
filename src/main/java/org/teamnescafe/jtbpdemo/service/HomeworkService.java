package org.teamnescafe.jtbpdemo.service;

import org.teamnescafe.jtbpdemo.entity.Homework;

import java.util.List;

public interface HomeworkService {

    void save(Homework homework);

    List<Homework> retrieveAllActiveHomework();

    List<Homework> retrieveAllHomework();
}