package com.example.application.backend.repository;

import com.example.application.backend.model.OMDBFilmSearch;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(name = "checkbemClient", url = "http://www.omdbapi.com", configuration = FeignClientProperties.FeignClientConfiguration.class)
public interface OMDBFeign {

    @RequestMapping(value = "?s={name}&apikey=5a97662",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.GET)
    OMDBFilmSearch findOmdbFilms(@PathVariable("name") String name);
}
