package com.example.patagoniansmovie.service;

import com.example.patagoniansmovie.dto.MovieDTO;
import com.example.patagoniansmovie.model.Movie;

import java.util.List;

public interface IMovieService {
    List<MovieDTO> getMovies();
    MovieDTO saveMovie(MovieDTO movieDTO);
    MovieDTO getMovieById(Integer id);
    Boolean updateMovie(Integer id, MovieDTO movieDTO);
    Boolean deleteById(Integer id);
    Movie getMovieByTitle(String title);
    Movie getMovieByDirector(String director);
}
