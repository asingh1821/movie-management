package com.org.media.repository;

import com.org.media.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long > {


    List<Genre> findByGenreName(String genre);
}
