package com.example.patagoniansmovie.service.impl;

import com.example.patagoniansmovie.dto.MovieDTO;
import com.example.patagoniansmovie.model.Movie;
import com.example.patagoniansmovie.repository.MovieRepository;
import com.example.patagoniansmovie.service.IMovieService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieServiceImpl implements IMovieService {
    
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private MovieRepository repository;

    @Override
    public List<MovieDTO> getMovies() {
        List<Movie> movies = repository.findAll();
        List<MovieDTO> moviesDTO = new ArrayList<>();
        movies.forEach(movie -> {
            MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
            moviesDTO.add(movieDTO);
        });
        return moviesDTO;
    }

    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        Movie newMovie = modelMapper.map(movieDTO, Movie.class);
        newMovie = repository.save(newMovie);
        log.info("Pelicula \"{}\" creada", newMovie.getTitle());
        return modelMapper.map(newMovie, MovieDTO.class);
    }

    @Override
    public MovieDTO getMovieById(Integer id) {
        Optional<Movie> optMovie = repository.findById(id);
        if (optMovie.isPresent()){
            Movie movie = optMovie.get();
            log.info("Película con id: \"{}\" encontrada", movie.getId());
            return modelMapper.map(movie, MovieDTO.class);
        }
        log.error("No se encontró la película con id: " + id);
        return new MovieDTO();
    }

    @Override
    public Boolean updateMovie(Integer id, MovieDTO movieDTO) {
        Optional<Movie> optMovie = repository.findById(id);
        if (optMovie.isPresent()){
            Movie movie = modelMapper.map(movieDTO, Movie.class);
            repository.save(movie);
            log.info("Pelicula: \"{}\" actualizada", movie.getTitle());
            return true;
        }
        log.error("Error al actualizar la pelicula");
        return false;
    }

    @Override
    public Boolean deleteById(Integer id){
        Optional<Movie> optMovie = repository.findById(id);
        if (optMovie.isPresent()){
            repository.deleteById(id);
            log.info("La película se borró correctamente");
            return true;
        }
        log.error("No se encontró la película indicada");
        return false;
    }

    @Override
    public Movie getMovieByTitle(String title){
        Optional<Movie> movie = repository.findMovieByTitle(title);
        log.info("Buscando película: " + title);
        return movie.orElseThrow(()-> new IllegalArgumentException("Película no encontrada"));
    }

    @Override
    public Movie getMovieByDirector(String director){
        Optional<Movie> movie = repository.findMovieByTitle(director);
        log.info("Buscando pelicula...");
        return movie.orElseThrow(()-> new IllegalArgumentException("Película no encontrada"));
    }
}
