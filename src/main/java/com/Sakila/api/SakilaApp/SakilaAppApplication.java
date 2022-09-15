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
	private ActorRepository actorRepository;
	private FilmRepository filmRepository;
	private CategoryRepository categoryRepository;


	public SakilaAppApplication(FilmRepository filmRepository, ActorRepository actorRepository, CategoryRepository categoryRepository){
		this.filmRepository = filmRepository;
		this.actorRepository = actorRepository;
		this.categoryRepository = categoryRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}

	@GetMapping("/allActors")
	@ResponseBody
	public
	Iterable<Actor> getAllActors(){
		return actorRepository.findAll();
	}

	@GetMapping("/Actor/{id}")
	@ResponseBody
	public Optional<Actor> getActorByID(@PathVariable Integer id){
		return actorRepository.findById(id);
	}

	@PostMapping("/Actor/add")
	@ResponseBody
	public String addActor(@RequestBody Actor actor){
		actorRepository.save(actor);
		return "Actor has been added!";
	}

	@DeleteMapping("/Actor/delete/{id}")
	@ResponseBody
	public String deleteActor(@PathVariable Integer id){
		actorRepository.deleteById(id);
		return "actor ID "+id+" has been deleted";
	}

	@PostMapping("/Actor/update/{id}")
	@ResponseBody
	public String updateActor(@PathVariable Integer id, @RequestParam String first_name, @RequestParam String last_name){
		Actor actor = actorRepository.findById(id).get();
		actor.first_name = first_name;
		actor.last_name = last_name;
		actorRepository.save(actor);
		return "Updated!!!";
	}

	@PostMapping("/Film/update")
	@ResponseBody
	public String updateFilm(@RequestBody Film Request){
		Film film = filmRepository.findById(Request.film_id).get();
		film.title = Request.title;
		filmRepository.save(film);
		System.out.println(Request.film_id +","+ Request.title);
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
