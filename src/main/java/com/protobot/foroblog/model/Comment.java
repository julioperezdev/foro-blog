package com.protobot.foroblog.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "Comment")
public class Comment {

    @Id
    private Long id;

    private String description;

    private Instant dates;

    private Long idForo;


    public Comment(Long id, String description, Instant dates, Long idForo) {
        this.id = id;
        this.description = description;
        this.dates = dates;
        this.idForo = idForo;
    }

    public Comment() {

    }
}
