package com.Sakila.api.SakilaApp;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.openqa.selenium.json.Json;

import javax.persistence.*;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Attribute

    int film_id;
    String title;
    String description;
    int rental_duration;
    double rental_rate;
    int length;
    double replacement_cost;

    //Constructor

    public Film(int id, String title, String description, int rental_duration, double rental_rate, int length, double replacement_cost){
        this.film_id = id;
        this.title = title;
        this.description = description;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.length = length;
        this.replacement_cost = replacement_cost;
    }
    public Film(){}

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID",this.film_id);
        jsonObject.put("Description", this.description);
        jsonObject.put("Name",this.title);
        jsonObject.put("Rental_Duration",this.rental_duration);
        jsonObject.put("Rental_Rate",this.rental_rate);
        jsonObject.put("Length",this.length);
        jsonObject.put("Replacement_Cost",this.replacement_cost);
        return jsonObject;
    }

    //Methods
    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(double rental_rate) {
        this.rental_rate = rental_rate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }
}
