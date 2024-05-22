package com.example.application.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OMDBFilm {
    private String Title;
    private String Year;
    private String Poster;
}
