package com.example.nhom2_case.model;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table (name = "image" )
public class Image {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idImage ;
    private String image ;
    @Transient
    private MultipartFile file;
    @ManyToOne
    private Home home ;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Image(Long idImage, String image, MultipartFile file, Home home) {
        this.idImage = idImage;
        this.image = image;
        this.file = file;
        this.home = home;
    }



    public Image() {
    }

    public Image(String image) {
        this.image = image;
    }

    public Image(Long idImage, String image, Home home) {
        this.idImage = idImage;
        this.image = image;
        this.home = home;
    }

    public Long getIdImage() {
        return idImage;
    }

    public void setIdImage(Long idImage) {
        this.idImage = idImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
