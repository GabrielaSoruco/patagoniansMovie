package com.example.patagoniansmovie.service;

import com.example.patagoniansmovie.dto.MovieDTO;
import com.example.patagoniansmovie.model.Movie;
import com.example.patagoniansmovie.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieService {
    
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private MovieRepository repository;

    public List<Movie> getMovies() {
        List<Movie> movies = repository.findAll();
        List<MovieDTO> moviesDTO = new ArrayList<>();
        movies.forEach(movie -> {
            MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
            moviesDTO.add(movieDTO);
        });
        /*
        for (Movie movie: movies) {
            MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
            moviesDTO.add(movieDTO);
        }
         */
        return movies;
    }

    public MovieDTO saveMovie(MovieDTO movieDTO) {
        Movie newMovie = modelMapper.map(movieDTO, Movie.class);
        newMovie = repository.save(newMovie);
        log.info("Pelicula \"{}\" creada", newMovie.getTitle());
        return modelMapper.map(newMovie, MovieDTO.class);
    }

    public MovieDTO getMovieById(Integer id) {
        Optional<Movie> optMovie = repository.findById(id);
        if (optMovie.isPresent()){
            Movie movie = repository.getMovieById(id);
            log.info("Película con id: \"{}\" encontrada", movie.getId());
            return modelMapper.map(movie, MovieDTO.class);
        }
        log.error("No se encontró la película con id: " + id);
        return new MovieDTO();
    }

    public Boolean updateMovie(Integer id, MovieDTO movieDTO) {
        Optional<Movie> optMovie = repository.findById(id);
        if (optMovie.isPresent()){
            Movie movie = modelMapper.map(movieDTO, Movie.class);
            //movie.setId(movieDTO.getId());
            //movie.setTitle(movieDTO.getTitle());
            //movie.setDirector(movieDTO.getDirector());
            //movie.setGenre(movieDTO.getGenre());
            repository.save(movie);
            log.info("Pelicula: \"{}\" actualizada", movie.getTitle());
            return true;
        }
        log.error("Error al actualizar la pelicula");
        return false;
    }

    public boolean deleteById(Integer id){
        Optional<Movie> optMovie = repository.findById(id);
        if (optMovie.isPresent()){
            repository.deleteById(id);
            log.info("La película se borró correctamente");
            return true;
        }
        log.error("No se encontró la película indicada");
        return false;
    }
}
