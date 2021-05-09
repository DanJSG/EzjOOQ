package com.jsg.jooqplayground;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "test")
public class Example {

    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private String value;

    @Column(name = "other")
    private String other;

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
