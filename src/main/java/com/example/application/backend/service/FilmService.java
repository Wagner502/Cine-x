package com.example.application.backend.service;

import com.example.application.backend.document.FilmDocument;
import com.example.application.backend.document.MovieSeatDocument;
import com.example.application.backend.model.FilmsTmdb;

import java.util.List;

public interface FilmService {
    List<FilmDocument> findAllFilmsByActiveTrue();

    void registerFilm(FilmDocument filmDocument);

    void deleteFilm(String id);

    MovieSeatDocument findAllMovieSeats(String filmId);
    List<MovieSeatDocument> findAllMovieSeatsByDocument(String document);
    FilmDocument findFilmById(String id);
    void saveMovieSeat(MovieSeatDocument movieSeatDocument);
    FilmsTmdb findTmdbNowPlaying();

}
