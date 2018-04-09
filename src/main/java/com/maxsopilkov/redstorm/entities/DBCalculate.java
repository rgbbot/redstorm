package com.maxsopilkov.redstorm.entities;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class DBCalculate {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
