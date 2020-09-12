package com.example.fistassigment;

public class Winners {
    private float lat = 0;
    private float lon = 0;
    private long timestamp = 0;
    private int numOfMoves = 99;
    private String name = "";
    private int player_number = 0;


    public Winners(String name, int player_number) {
        this.name = name;
        this.player_number = player_number;
    }

    public Winners() {
    }

    public Winners(float lat, float lon, long timestamp, int numOfMoves, String name, int player_number) {
        this.lat = lat;
        this.lon = lon;
        this.timestamp = timestamp;
        this.numOfMoves = numOfMoves;
        this.name = name;
        this.player_number = player_number;

    }

    public double getLat() {
        return lat;
    }

    public Winners setLat(float lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Winners setLon(float lon) {
        this.lon = lon;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Winners setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
