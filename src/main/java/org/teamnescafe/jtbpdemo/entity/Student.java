package org.teamnescafe.jtbpdemo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "f_l_name")
    private String firstAndLastName;

    @Column(name = "birthday")
    private Date birthday;

    @Override
    public String toString() {
        return String.format("%d. %s. Дата рождения: %s", id, firstAndLastName, birthday.toString());
    }
}

