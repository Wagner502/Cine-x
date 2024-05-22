package com.example.application.backend.repository;

import com.example.application.backend.document.FilmDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FilmRepository extends MongoRepository<FilmDocument, String> {
    List<FilmDocument> findAllByActiveIsTrue();
}
