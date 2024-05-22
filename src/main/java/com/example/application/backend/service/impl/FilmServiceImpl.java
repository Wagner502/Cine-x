package com.example.application.backend.service.impl;

import com.example.application.backend.document.FilmDocument;
import com.example.application.backend.document.MovieSeatDocument;
import com.example.application.backend.model.FilmsTmdb;
import com.example.application.backend.repository.FilmRepository;
import com.example.application.backend.repository.MovieSeatRepository;
import com.example.application.backend.repository.TMDBRepository;
import com.example.application.backend.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository;
    private MovieSeatRepository movieSeatRepository;
    private TMDBRepository tmdbRepository;

    @Override
    public List<FilmDocument> findAllFilmsByActiveTrue() {
        return filmRepository.findAllByActiveIsTrue();
    }

    @Override
    public void registerFilm(FilmDocument filmDocument) {
        var id = filmDocument.getId();
        if (isNotBlank(id)) {
            var oldDocument = filmRepository.findById(id);
            oldDocument.ifPresent(document -> {
                editDocument(filmDocument, document);

                filmRepository.save(document);
            });
        } else {
            filmRepository.save(filmDocument);
        }


    }

    private void editDocument(FilmDocument filmDocument, FilmDocument oldDocument) {
        oldDocument.setName(filmDocument.getName());
        oldDocument.setDescription(filmDocument.getDescription());
        oldDocument.setUrlImage(filmDocument.getUrlImage());
        oldDocument.setDuration(filmDocument.getDuration());
        oldDocument.setInitDate(filmDocument.getInitDate());
        oldDocument.setFinalDate(filmDocument.getFinalDate());
        oldDocument.setActive(filmDocument.isActive());
    }

    @Override
    public void deleteFilm(String id) {
        filmRepository.deleteById(id);
    }

    @Override
    public MovieSeatDocument findAllMovieSeats(String filmId) {
        var document = movieSeatRepository.findByFilmId(filmId)
                .orElse(new MovieSeatDocument());
        return document;
    }

    @Override
    public List<MovieSeatDocument> findAllMovieSeatsByDocument(String document) {
        return movieSeatRepository.findBySeatsDocument(document);
    }

    @Override
    public FilmDocument findFilmById(String id) {
        return filmRepository.findById(id)
                .orElse(new FilmDocument());
    }

    @Override
    public void saveMovieSeat(MovieSeatDocument movieSeatDocument) {
        var document = movieSeatRepository.findByFilmId(movieSeatDocument.getFilmId())
                .orElse(new MovieSeatDocument());

        if(isBlank(document.getFilmId())) {
            movieSeatRepository.save(movieSeatDocument);
        } else {
            document.setSeats(movieSeatDocument.getSeats());
            movieSeatRepository.save(document);
        }



    }

    @Override
    public FilmsTmdb findTmdbNowPlaying() {
        var token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ZmVjYzhjYmFiNmNjMTM2ZDcyNDJiNTdhMDA4OTMwMSIsInN1YiI6IjY2NDhlYjUxMGQ2Y2Q2ZjUwZjJmNzQyZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.aUB46BYEchoivy1Z_NtmKfVMmMSdu4WIaTCnVN8D7Og";
        return tmdbRepository.findTmdbNowPlaying(token);
    }
}
