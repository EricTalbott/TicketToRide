package com.example.java;

/**
 * Created by eric on 1/10/17.
 */
public class Players {
    private int score = 12;
    private int id;
    private int trains = 5;
    private int cards = 4;
    private int stations = 3;
    private int routes = 0;
    private String name;


    public void setName(String name) {this.name = name;}
    public void setId(int num) {this.id = num;}
    public void setScore(int i){this.score += i;}
    public void setStations(int stations) {
        this.stations -= stations;
    }
    public void setCards(int cards) {
        this.cards += cards;
    }
    public void setTrains(int i){ this.trains -= i;}
    public void setRoutes(int routes) {this.routes += routes;}

    public String getName(){return name;}
    public int getId(){return id;}
    public int getScore() {return score;}
    public int getTrains() {return trains;}
    public int getStations() {
        return stations;
    }
    public int getCards() {
        return cards;
    }
    public int getRoutes() {return routes;}

}
