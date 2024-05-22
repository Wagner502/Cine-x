package com.example.application.backend.repository;

import com.example.application.backend.document.MovieSeatDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MovieSeatRepository extends MongoRepository<MovieSeatDocument, String> {
    Optional<MovieSeatDocument> findByFilmId(String id);

    List<MovieSeatDocument> findBySeatsDocument(String document);
}
