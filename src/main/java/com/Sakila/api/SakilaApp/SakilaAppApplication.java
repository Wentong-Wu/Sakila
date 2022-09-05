package com.Sakila.api.SakilaApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/Home")
@CrossOrigin
public class SakilaAppApplication {
	@Autowired
	private ActorRepository actorRepository;
	private FilmRepository filmRepository;
	public SakilaAppApplication(ActorRepository actorRepository){
		this.actorRepository = actorRepository;
	}
	public SakilaAppApplication(FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
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

	@GetMapping("allFiims")
	@ResponseBody
	public Iterable<Film> getAllFilms(){return filmRepository.findAll();}
	@PostMapping("/Film/{id}")
	@ResponseBody
	public Optional<Film> getFilmByID(@PathVariable Integer id){
		return filmRepository.findById(id);
	}

	@PostMapping("RandomEnemyPokeFilm")
	@ResponseBody
	public String generateEnemy(){
		return "String";
	}
}
