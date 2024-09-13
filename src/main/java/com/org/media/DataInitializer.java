package com.org.media;

import com.org.media.entity.Genre;
import com.org.media.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public void run(String... args) throws Exception{
        String[] gen = {"thriller", "romance", "action", "horror"};

        for (String g : gen){
            if (genreRepository.findByGenreName(g).isEmpty()) {
                Genre genre = new Genre();
                genre.setGenreName(g);
                genreRepository.save(genre);
            }
        }
    }
}
