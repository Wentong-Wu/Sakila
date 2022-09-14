package com.Sakila.api.SakilaApp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;
import java.util.Optional;

public interface FilmRepository extends CrudRepository<Film, Integer> {
//    @Query(nativeQuery = true, value = "SELECT film.title FROM film WHERE film_id = :id limit 1")
//    Optional<String> getFilmTitle(@PathVariable Integer id);
//    @Query(nativeQuery = true, value = "SELECT film.length FROM film WHERE film_id = :id limit 1")
//    Optional<Integer> getFilmLength(@PathVariable Integer id);
//
//    @Query(nativeQuery = true, value = "SELECT film.description FROM film WHERE film_id = :id limit 1")
//    Optional<String> getFilmDescription(@PathVariable Integer id);
//
//    @Query(nativeQuery = true, value = "SELECT film.rental_duration FROM film WHERE film_id = :id limit 1")
//    Optional<Integer> getFilmRentalDuration(@PathVariable Integer id);
//
//    @Query(nativeQuery = true, value = "SELECT film.rental_rate FROM film WHERE film_id = :id limit 1")
//    Optional<Double> getFilmRentalRate(@PathVariable Integer id);
//
//    @Query(nativeQuery = true, value = "SELECT film.replacement_cost FROM film WHERE film_id = :id limit 1")
//    Optional<Double> getFilmReplacementCost(@PathVariable Integer id);
//
//    @Query(nativeQuery = true, value = "SELECT film.rating FROM film WHERE film_id = :id limit 1")
//    Optional<String> getFilmRating(@PathVariable Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM film order by rand() limit 6")
    Iterable<Film> getRandom6Films();

    @Query(nativeQuery = true, value = "SELECT film.*, category.category_id, category.name FROM film INNER JOIN film_category ON film.film_id = film_category.film_id INNER JOIN category ON film_category.category_id = category.category_id WHERE category.category_id = film_category.category_id AND film.film_id = film_category.film_id")
    Iterable<Film> getCategoryFilm();

}
