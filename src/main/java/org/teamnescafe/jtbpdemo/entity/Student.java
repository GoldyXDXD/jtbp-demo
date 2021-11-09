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
        return String.format("%s. Дата рождения: %s.%s.%s", firstAndLastName, birthday.toString().substring(8, 10), birthday.toString().substring(5, 7), birthday.toString().substring(0, 4));
    }
}

