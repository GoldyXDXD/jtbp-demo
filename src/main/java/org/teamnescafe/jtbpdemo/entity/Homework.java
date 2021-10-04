package org.teamnescafe.jtbpdemo.entity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "homework")
public class Homework {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "homework_description")
    private String description;

    @Column(name = "active")
    private boolean active;
}
