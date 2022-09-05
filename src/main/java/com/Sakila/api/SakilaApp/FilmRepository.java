package com.Sakila.api.SakilaApp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface FilmRepository extends CrudRepository<Film, Integer> {
    @Query(nativeQuery = true, value = "SELECT film.length FROM film WHERE film_id = :id limit 1")
    Optional<Integer> getFilmLength(@PathVariable Integer id);
}
