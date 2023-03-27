package com.example.TestProject.repository;

import com.example.TestProject.entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface MovieRepository extends CrudRepository<Movie, Long> {

    Optional<Movie> getByimdbID(String imdbID);

    Long deleteByimdbID(String imdbID);
}
