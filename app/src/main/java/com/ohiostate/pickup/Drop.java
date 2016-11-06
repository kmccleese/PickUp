package com.ohiostate.pickup;

import java.util.Date;


public class Drop {

    int id;
    int player_id;
    String sport;
    String location;
    Date play_time;
    String difficulty;
    Date submit_time;
    int num_players;
    String gender;

    public void setId() { this.id = id; }
    public int getId() { return id; }
    public void setPlayer_id() { this.player_id = id; }
    public int getPlayer_id () { return player_id; }
    public void setSport() { this.sport = sport; }
    public String getSport() { return sport; }
    public void setLocation() { this.location = location; }
    public String getLocation() { return location; }
    public void setDifficulty() { this.difficulty = difficulty; }
    public String getDifficulty() { return difficulty; }
    public void setPlay_time() { this.play_time = play_time; }
    public Date getPlay_time() { return play_time; }
    public void setSubmit_time() { this.submit_time = submit_time; }
    public Date getSubmit_time() { return submit_time;}
    public void setNum_players() { this.num_players = num_players; }
    public int getNum_players() { return num_players; }
    public void setGender() { this.gender = gender; }
    public String getGender() { return gender; }
}
