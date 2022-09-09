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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootApplication
@RestController
@RequestMapping("/Home")
@CrossOrigin
public class SakilaAppApplication {
	Random rand = new Random();
	@Autowired
	private ActorRepository actorRepository;
	private FilmRepository filmRepository;



	public SakilaAppApplication(FilmRepository filmRepository, ActorRepository actorRepository){
		this.filmRepository = filmRepository;
		this.actorRepository = actorRepository;
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

	@GetMapping("allFilms")
	@ResponseBody
	public Iterable<Film> getAllFilms(){return filmRepository.findAll();}

	@GetMapping("/ChooseFilm/{id}")
	@ResponseBody
	public Optional<Film> getFilmByID(@PathVariable Integer id){
		return filmRepository.findById(id);
	}

	@GetMapping("/AiPokeFilms")
	@ResponseBody
	public Iterable<Film> getAiPokeFilms(){
		return filmRepository.findAll();
	}

	@GetMapping("RandomEnemyPokeFilm")
	@ResponseBody
	public String generateEnemy(){
		ArrayList<Integer> FilmIDGenerate = new ArrayList<Integer>();
		ArrayList<JSONObject> pokeFilms = new ArrayList<JSONObject>();
		Film pokeFilm = null;
		for(int i=0;i<6;i++) {
			FilmIDGenerate.add(rand.nextInt(1, 1000));
			String title = filmRepository.getFilmTitle(FilmIDGenerate.get(i)).get();
			String description = filmRepository.getFilmDescription(FilmIDGenerate.get(i)).get();
			int rental_duration = filmRepository.getFilmRentalDuration(FilmIDGenerate.get(i)).get();
			double rental_rate = filmRepository.getFilmRentalRate(FilmIDGenerate.get(i)).get();
			int length = filmRepository.getFilmLength(FilmIDGenerate.get(i)).get();
			double replacement_cost = filmRepository.getFilmReplacementCost(FilmIDGenerate.get(i)).get();
			String rating = filmRepository.getFilmRating(FilmIDGenerate.get(i)).get();
			pokeFilm = new Film(FilmIDGenerate.get(i),title,description,rental_duration,rental_rate,length,replacement_cost,rating);
			pokeFilms.add(pokeFilm.toJson());
		}
		return pokeFilms.toString();
	}

	@GetMapping("/GetFilmByCate")
	@ResponseBody
	public Iterable<Film> getFilmByCate() { return filmRepository.getCategoryFilm();}

	@GetMapping("/GetPokeFilm/{id}")
	@ResponseBody
	public Optional<Film> getPokeFilm(@PathVariable Integer id){
		return filmRepository.getAllPokeData(id);
	}
}
