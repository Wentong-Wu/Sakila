package com.sakila.api.sakilaapp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM film order by rand() limit 6")
    Iterable<Film> getRandom6Films();

    @Query(nativeQuery = true, value = "SELECT film.*, category.category_id, category.name FROM film INNER JOIN film_category ON film.film_id = film_category.film_id INNER JOIN category ON film_category.category_id = category.category_id WHERE category.category_id = film_category.category_id AND film.film_id = film_category.film_id")
    Iterable<Film> getCategoryFilm();

}
