package org.teamnescafe.jtbpdemo.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "description")
    private String description; // название предмета

    @Column(name = "teacher")
    private String teacher;

    @Column(name = "date")
    private Date date;

    @Column(name = "beginning")
    private Time beginning;

    @Column(name = "end")
    private Time end;

    @Override
    public String toString() {
        if (getTeacher() == null || getTeacher().equals("")) {
            return String.format("%s. %s - %s\n", description, beginning.toString(), end.toString());
        } else {
            return String.format("%s. %s - %s\nПреподаватель: %s\n", description, beginning.toString(), end.toString(), teacher);
        }
    }
}
