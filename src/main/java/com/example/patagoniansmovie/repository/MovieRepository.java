package com.example.patagoniansmovie.repository;

import com.example.patagoniansmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findMovieByTitle(String title);

    Optional<Movie> findMovieByDirector(String director);
}
