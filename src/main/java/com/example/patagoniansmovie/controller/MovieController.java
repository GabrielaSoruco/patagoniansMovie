package com.example.patagoniansmovie.controller;

import com.example.patagoniansmovie.dto.MovieDTO;
import com.example.patagoniansmovie.model.Movie;
import com.example.patagoniansmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> findAllMovies(){
        return new ResponseEntity<>(service.getMovies(), HttpStatus.OK);
    }

    @PostMapping("/addMovie")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDTO){
        return new ResponseEntity<>(service.saveMovie(movieDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Integer id){
        return new ResponseEntity<>(service.getMovieById(id), HttpStatus.OK);
    }

    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody MovieDTO movieDTO){
        if (service.updateMovie(id, movieDTO)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("NO SE ENCONTRÓ REGISTRO CON ID: " + id, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id){
        if(service.deleteById(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("NO SE ENCONTRÓ REGISTRO CON ID: " + id, HttpStatus.BAD_REQUEST);
    }
}
