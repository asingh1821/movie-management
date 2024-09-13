package com.org.media.repository;


import com.org.media.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Movie, Long > {

    Optional<Movie> existsByName(String name);
    @Query(value = "SELECT name FROM movie WHERE name = ?1", nativeQuery = true)
    List<String> findByName(String name);


}
