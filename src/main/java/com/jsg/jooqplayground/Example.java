package com.jsg.jooqplayground;

import com.jsg.jooqplayground.ezsql.Entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "test")
public class Example implements Entity {

    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private String value;

    @Column(name = "value")
    private String other;

    @Override
    public String toString() {
        return "Example{" +
                "value='" + value + '\'' +
                ", other='" + other + '\'' +
                '}';
    }

}
