package com.ohiostate.pickup;

import java.util.Date;
import java.util.Random;


public class Drop {

    int id;
    int player_id;
    String sport;
    String location;
    Date play_time;
    String difficulty;
    Date date;
    int num_players;
    String gender;
    String message;

    public Drop() {
        Random random = new Random();
        id = random.nextInt(500);
    }

    public Drop(int given_id) {
        id = given_id;
        date = new Date();
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public void setPlayer_id(int player_id) { this.player_id = player_id; }
    public int getPlayer_id () { return player_id; }
    public void setSport(String sport) { this.sport = sport; }
    public String getSport() { return sport; }
    public void setLocation(String location) { this.location = location; }
    public String getLocation() { return location; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getDifficulty() { return difficulty; }
    public void setPlay_time(Date play_time) { this.play_time = play_time; }
    public Date getPlay_time() { return play_time; }
    public void setDate(Date date) { this.date = date; }
    public Date getDate() { return date;}
    public void setNum_players(int num_players) { this.num_players = num_players; }
    public int getNum_players() { return num_players; }
    public void setGender(String gender) { this.gender = gender; }
    public String getGender() { return gender; }
    public void setMessage(String message) { this.message = message; }
    public String getMessage() { return message; }
}
