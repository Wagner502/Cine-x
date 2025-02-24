package com.example.application.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OMDBFilmSearch {
    @JsonProperty("Search")
    private List<OMDBFilm> Search;
}
