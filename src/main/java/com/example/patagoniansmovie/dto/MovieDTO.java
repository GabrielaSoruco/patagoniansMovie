package com.example.patagoniansmovie.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class MovieDTO implements Serializable {
    private Integer id;
    private String title;
    private String director;
    private String genre;
}
