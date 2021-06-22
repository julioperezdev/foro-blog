package com.protobot.foroblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "Category")
public class Category {
    @Id
    private Long id;

    private String name;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {

    }
}
