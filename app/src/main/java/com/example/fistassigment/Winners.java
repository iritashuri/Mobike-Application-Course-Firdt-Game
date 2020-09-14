package com.example.fistassigment;

import java.util.Date;

public class Winners {
    private double lat = 0;
    private double lon = 0;
    private String timestamp = "";
    private int numOfMoves = 99;
    private String name = "";
    private int player_number = 0;


    public Winners(String name, int player_number) {
        this.name = name;
        this.player_number = player_number;
        this.timestamp = java.text.DateFormat.getDateTimeInstance().format(new Date());
    }

    public Winners() {
        this.timestamp = java.text.DateFormat.getDateTimeInstance().format(new Date());
    }

    public Winners(float lat, float lon, String timestamp, int numOfMoves, String name, int player_number) {
        this.lat = lat;
        this.lon = lon;
        this.timestamp = timestamp;
        this.numOfMoves = numOfMoves;
        this.name = name;
        this.player_number = player_number;
        //Set time
        this.timestamp = java.text.DateFormat.getDateTimeInstance().format(new Date());

    }

    public double getLat() {
        return lat;
    }

    public Winners setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Winners setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Winners setTimestamp(String timestamp) {
        this.timestamp = java.text.DateFormat.getDateTimeInstance().format(new Date());
        return this;
    }

    public int getNumOfMoves() {
        return numOfMoves;
    }

    public Winners setNumOfMoves(int numOfMoves) {
        this.numOfMoves = numOfMoves;
        return this;
    }

    public String getName() {
        return name;
    }

    public Winners setName(String name) {
        this.name = name;
        return this;
    }


    public int getPlayer_number() {
        return player_number;
    }

    public Winners setPlayer_number(int player_number) {
        this.player_number = player_number;
        return this;
    }
}
