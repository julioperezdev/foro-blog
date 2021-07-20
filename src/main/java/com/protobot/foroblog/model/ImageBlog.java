package com.protobot.foroblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "ImageBlog")
public class ImageBlog {

    @Id
    private int id;

    @Column(name = "idBlog")
    private int idBlog;

    @Column(name = "imageUrl")
    private String imageUrl;

    private String publicId;

    public ImageBlog(int idBlog, String imageUrl, String publicId) {
        this.idBlog = idBlog;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
    }

    public ImageBlog(int id, int idBlog, String imageUrl, String publicId) {
        this.id = id;
        this.idBlog = idBlog;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
    }

    public ImageBlog() {

    }
}
