package com.Sakila.api.SakilaApp;

public class PokeFilm {
    public String name;
    public int id, health, attack;

    public PokeFilm(int id){
        this.id = id;
    }
    public String filmStatus(){
        return "ID: "+id+"Attack Power: "+attack+" Health: "+ health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
