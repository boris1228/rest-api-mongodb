package com.example.restapimongodb.services;


import com.example.restapimongodb.models.Movie;
import com.example.restapimongodb.models.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

//import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService

{

    @Autowired
    private MovieRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> getMovies()

    {

        // business logics
        return repository.findAll();
    }


    public List<Movie> getMovieList(){
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is("1"));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getShowList(){
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is("2"));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getMoviesWithTitle(String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex("^.*"+title+".*$", "i"));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getFeaturedMovies(String feature){
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is("1")).addCriteria(Criteria.where("feature").is(feature));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public List<Movie> getFeaturedShows(String feature){
        Query query = new Query();
        query.addCriteria(Criteria.where("genre").is("2")).addCriteria(Criteria.where("feature").is(feature));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public void insertIntoMovies(Movie movie)
    {

        repository.insert(movie);

    }

    public Movie editMovie(String id, Movie newMovieData)
    {
        // get the resource based on the id

        Optional<Movie> movie = repository.findById(id);

        // validation code to validate the id


        movie.get().setGenre(newMovieData.getGenre());
        movie.get().setSmallPoster(newMovieData.getSmallPoster());
        movie.get().setBigPoster(newMovieData.getBigPoster());
        movie.get().setTitle(newMovieData.getTitle());
        movie.get().setDescription(newMovieData.getDescription());
        movie.get().setRentalPrice(newMovieData.getRentalPrice());
        movie.get().setPurchasePrice(newMovieData.getPurchasePrice());
        movie.get().setRating(newMovieData.getRating());
        movie.get().setFeature(newMovieData.getFeature());

        Movie updateMovie = repository.save(movie.get());

        return updateMovie;



    }

    public Optional<Movie> getAMovie(String id) throws Exception
    {

        Optional<Movie> movie = repository.findById(id);

        // This is saying that if movie ref variable does not contain a value then

        if (!movie.isPresent())
        {
            throw new Exception (" Movie with " + id + " is not found ");
        }

        return movie;

    }

    public void deleteAMovie( String id)
    {
        repository.deleteById(id);
    }

}