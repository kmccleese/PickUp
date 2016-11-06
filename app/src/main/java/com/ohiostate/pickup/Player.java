package com.ohiostate.pickup;



public class Player {

    int id;
    String first_name;
    String last_name;
    String email;
    String gender;

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public void setFirst_name(String first_name) { this.first_name = first_name;}
    public String getFirst_Name() {
        return first_name;
    }
    public void setLast_name(String last_name) { this.last_name = last_name; }
    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }
    public void setGender(String gender) { this.gender = gender;}
    public String getGender() { return gender; }
}
