package com.org.media.service;

import com.org.media.entity.Movie;

import java.util.List;

public interface MediaService {

    String saveName(Movie movie);
    String saveAll(List<Movie> movie);

    Movie getMovieById(Long id);

   List<Movie> getAllMovie();

    Movie updateMovie(Long id, Movie movie);

     void deleteMovie(Long id);

}
