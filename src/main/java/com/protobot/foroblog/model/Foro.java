package com.protobot.foroblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "Foro")
public class Foro {

    @Id
    private Long id;

    private String name;

    private Instant dates;

    private Long idCategory;

    public Foro(Long id, String name, Instant dates, Long idCategory) {
        this.id = id;
        this.name = name;
        this.dates = dates;
        this.idCategory = idCategory;
    }

    public Foro() {

    }
}
