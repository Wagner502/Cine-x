package com.example.application.backend.repository;

import com.example.application.backend.model.FilmsTmdb;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tmdbClient", url = "https://api.themoviedb.org/3/movie/now_playing", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface TMDBRepository {

    @RequestMapping(value = "?language=pt-BR&region=BR",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    FilmsTmdb findTmdbNowPlaying(@RequestHeader("Authorization") String token);
}
