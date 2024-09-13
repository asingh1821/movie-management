package com.org.media.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Movie")
@Entity
@Builder
public class Movie {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String genre;

    public Movie(Movie movie) {
    }
}
