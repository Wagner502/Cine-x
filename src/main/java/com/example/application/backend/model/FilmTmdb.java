package com.example.application.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmTmdb {
    private String id;
    private String title;
    private String overview;
    private LocalDate release_date;
    private String poster_path;
    private String backdrop_path;

    private Double price = 19.90;
    private String session;
}
