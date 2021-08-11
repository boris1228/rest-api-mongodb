package com.example.restapimongodb.controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.Movie;
import com.example.restapimongodb.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class MovieController

{

    @Autowired
    private MovieService service;


    @GetMapping("/movies")
    public ResponseEntity getMovies()
    {


        var customizedResponse = new CustomizedResponse(" A list of movies" , service.getMovies());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


//    @GetMapping("/movies/rating")
//    public ResponseEntity getmoviesByRating(@RequestParam(value = "rate") String r)
//    {
//
//        var customizedResponse = new CustomizedResponse(" A list of movies with the rating : " , service.getMoviesWithRating(r));
//
//        return new ResponseEntity(customizedResponse, HttpStatus.OK);
//    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getAMovie(@PathVariable("id") String id) {


        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse(" Movie with id " + id , Collections.singletonList(service.getAMovie(id)));
        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);

        }
//        System.out.println(id);
//        System.out.println(service.getAMovie(id));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);

    }

    @GetMapping("/movies/movie-list")
    public ResponseEntity getMovieList(){
        var customizedResponse = new CustomizedResponse( " A list of movies", service.getMovieList());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/tv-list")
    public ResponseEntity getShowList(){
        var customizedResponse = new CustomizedResponse( " A list of tv shows", service.getShowList());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/title")
    public ResponseEntity getMoviesByTitle(@RequestParam(value="title") String title){
        var customizedResponse = new CustomizedResponse( " A list of movies with title", service.getMoviesWithTitle(title));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/movie-list/feature")
    public ResponseEntity getFeaturedMovies(@RequestParam(value="feature") String feature){
        var customizedResponse = new CustomizedResponse( " A list of featured movies", service.getFeaturedMovies(feature));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/tv-list/feature")
    public ResponseEntity getFeaturedShows(@RequestParam(value="feature") String feature){
        var customizedResponse = new CustomizedResponse( " A list of featured tv shows", service.getFeaturedShows(feature));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteAMovie(@PathVariable("id") String id)
    {
        CustomizedResponse customizedResponse = null;
        try {
            Optional<Movie> movie = service.getAMovie(id);
            service.deleteAMovie(id);
            customizedResponse = new CustomizedResponse( " Movie " + id + " was deleted successfully", null);
        }catch (Exception e){
            customizedResponse = new CustomizedResponse( "Movie " + id + " is not found on the database", null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/movies", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addMovie(@RequestBody Movie movie)
    {
        CustomizedResponse customizedResponse = null;
        try {

            service.insertIntoMovies(movie);
            customizedResponse = new CustomizedResponse(" Movie is added successfully", null);
        }catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(movie, HttpStatus.OK);

    }

    @PutMapping(value = "/movies/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editMovie(@PathVariable("id") String id, @RequestBody Movie newMovie )

    {
        try {


            var customizedResponse = new CustomizedResponse(" Movie with ID:  " + id + "was updated successfully ", Collections.singletonList(service.editMovie(id, newMovie)));

            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }



    }
}
