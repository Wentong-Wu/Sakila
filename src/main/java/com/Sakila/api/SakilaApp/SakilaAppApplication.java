package com.Sakila.api.SakilaApp;

import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.security.SecureRandom;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/Home")
@CrossOrigin("*") //Sensitive
public class SakilaAppApplication {
	SecureRandom rand = new SecureRandom();
	@Autowired
	private FilmRepository filmRepository;
	private CategoryRepository categoryRepository;


	public SakilaAppApplication(FilmRepository filmRepository, CategoryRepository categoryRepository){
		this.filmRepository = filmRepository;
		this.categoryRepository = categoryRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}

	@PostMapping("/Film/add")
	@ResponseBody
	public String addActor(@RequestBody Film film){
		filmRepository.save(film);
		return "Film has been added!";
	}

	@DeleteMapping("/Film/delete/{id}")
	@ResponseBody
	public String deleteFilm(@PathVariable Integer id){
		filmRepository.deleteById(id);
		return "actor ID "+id+" has been deleted";
	}

	@PostMapping("/Film/update")
	@ResponseBody
	public String updateFilm(@RequestBody Film Request){
		Optional<Film> optional = filmRepository.findById(Request.film_id);
		if(optional.isEmpty()) {
			throw new NoSuchElementException();
		}
		Film film = optional.get();
		film.title = Request.title;
		filmRepository.save(film);
		System.out.println(Request.film_id + "," + Request.title);
		return "UPDATED";
	}


	@GetMapping("/generateRandom")
	@ResponseBody
	public Iterable<Film> getGenerateRandom(){return filmRepository.getRandom6Films();}

	@GetMapping("allFilms")
	@ResponseBody
	public Iterable<Film> getAllFilms(){return filmRepository.findAll();}

	@GetMapping("/ChooseFilm/{id}")
	@ResponseBody
	public Optional<Film> getFilmByID(@PathVariable Integer id){
		return filmRepository.findById(id);
	}

	@GetMapping("/allCategory")
	@ResponseBody
	public Iterable<Category> getAllCategory(){return categoryRepository.findAll();}

	@GetMapping("/CategoryByFilmID/{id}")
	@ResponseBody
	public Optional<Category> getCategoryByFilmID(@PathVariable Integer id){return categoryRepository.findById(id);}

	@GetMapping("/GetFilmByCate")
	@ResponseBody
	public Iterable<Film> getFilmByCate() { return filmRepository.getCategoryFilm();}

}
