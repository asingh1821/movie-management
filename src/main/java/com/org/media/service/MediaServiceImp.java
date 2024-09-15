package com.org.media.service;

import com.org.media.entity.Genre;
import com.org.media.entity.Movie;
import com.org.media.repository.GenreRepository;
import com.org.media.repository.MediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaServiceImp implements  MediaService{

    Logger logger = LoggerFactory.getLogger(MediaServiceImp.class);


    @Autowired
    private  MediaRepository mediaRepository;

    @Autowired
    private GenreRepository genreRepository;

    public MediaServiceImp(MediaRepository mediaRepository){
        this.mediaRepository = mediaRepository;
    }

    @Override
    public String saveName(Movie movie) {
        List<String> existsMovie  = mediaRepository.findByName(movie.getName());
        logger.warn("Existing Movie = {}", existsMovie);
//        if(existsMovie.isPresent()){
//            return "movie already present in database";
//        } else {
//            mediaRepository.save(movie);
//            return "movie added in the database";
//        }
        int movieAdded = 0,notAddedMovie = 0;

       if(existsMovie.contains(movie.getName())){
            return "notSaved";
        } else {
           List<Genre> listGen = genreRepository.findByGenreName(movie.getGenre());
           if (listGen.contains(movie.getGenre())) {
               return "Genre not present";
           } else {
               mediaRepository.save(movie);
               return "saved";
           }
       }

    }

    @Override
    public String saveAll(List<Movie> movie) {
         int savedMovies = 0, notSavedMovies = 0;
        for(Movie m : movie) {
            String s = saveName(m);
            if ("saved".equals(s)) {
                savedMovies++;
            } else {
                notSavedMovies++;
            }
        }
        return ("Movie not added "+notSavedMovies +"   movie added "+ savedMovies);

    }


    @Override
    public Movie getMovieById(Long id){
        return mediaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found" + id));
     }
     @Override
     public List<Movie> getAllMovie(){
        return mediaRepository.findAll();
     }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
                Movie movie1 =  mediaRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Movie not found" + id));
                logger.info("movie fetched");
                movie1.setName(movie.getName());
                movie1.setGenre(movie.getGenre());
                return mediaRepository.save(movie1);
    }

    @Override
    public void deleteMovie(Long id) {
         mediaRepository.deleteById(id);
    }

}
