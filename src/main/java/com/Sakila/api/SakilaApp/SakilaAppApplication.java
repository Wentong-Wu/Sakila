package com.Sakila.api.SakilaApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("/Home")
@CrossOrigin

public class SakilaAppApplication {
	@Autowired
	private ActorRepository actorRepository;
	public SakilaAppApplication(ActorRepository actorRepository){
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
}
