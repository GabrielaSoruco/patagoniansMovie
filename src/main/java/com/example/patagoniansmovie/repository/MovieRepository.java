package com.example.patagoniansmovie.repository;

import com.example.patagoniansmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie getMovieById(Integer id);
}
