package org.cx.entity;

import javax.persistence.*;

/**
 * @author grass
 * @date 2018/1/2
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
