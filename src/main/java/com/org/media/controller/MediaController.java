package com.org.media.controller;

import com.org.media.entity.Movie;
import com.org.media.service.MediaService;
import com.org.media.service.MediaServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MediaController {
    Logger logger = LoggerFactory.getLogger(MediaController.class);
    @Autowired
    private  MediaService mediaService;

   public MediaController(MediaService mediaService){
       this.mediaService = mediaService;
   }

    @PostMapping("/movie")
    public String saveMovieName(@RequestBody Movie movie){
        return mediaService.saveName(movie);
    }

    @PostMapping("/movies")
    public ResponseEntity<String> saveAll(@RequestBody List<Movie> movie){
       String message = mediaService.saveAll(movie);
       return new ResponseEntity<>(message,HttpStatus.OK);
    }


    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie>getMovieById(@PathVariable Long id){
       Movie newMovie1 = mediaService.getMovieById(id);
       return ResponseEntity.ok(newMovie1);
    }
    @GetMapping("/movie")
    public List<Movie> getAllMovie(){
       return mediaService.getAllMovie();

    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie>updateMovie(@PathVariable Long id, @RequestBody Movie movie){
      Movie newUpdateMovie = mediaService.updateMovie(id, movie);
      return new ResponseEntity<>(newUpdateMovie, HttpStatus.OK);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<String> deletemovie(@PathVariable Long id){
       mediaService.deleteMovie(id);
       return ResponseEntity.ok("Movie deleted successfully");
    }
}
