package com.example.TestProject.controller;

import com.example.TestProject.entity.Movie;
import com.example.TestProject.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class MoviesController {

    @Autowired
    MovieRepository movieRepository;

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {

        Movie savedMovie = movieRepository.save(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @GetMapping("/movie/{imdbID}")
    public ResponseEntity<Movie> getMovieByImdbID(@PathVariable("imdbID") String imdbID) {

        Optional<Movie> movie = movieRepository.getByimdbID(imdbID);

        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movie/{imdbID}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable("imdbID") String imdbID) {

        Optional<Movie> movie = movieRepository.getByimdbID(imdbID);
        if (movie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            movieRepository.deleteByimdbID(imdbID);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}