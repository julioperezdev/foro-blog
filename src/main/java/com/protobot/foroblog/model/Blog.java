package com.protobot.foroblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "Blog")
public class Blog {

    @Id
    private Long id;

    private String name;

    private Instant dates;

    private String description;



    public Blog(Long id, String name, Instant dates, String description) {
        this.id = id;
        this.name = name;
        this.dates = dates;
        this.description = description;
    }
    public Blog() {}


}
