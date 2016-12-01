package com.ohiostate.pickup;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by kellymccleese on 11/30/16.
 */

public class PlayerTest {

    private Player mPlayer;
    long id = 123456;
    @Test
    public void newPlayer() throws Exception {
        mPlayer = new Player(id);
    }
    @Test
    public void playerGetId1(){
        mPlayer = new Player(1234);
        assertEquals(1234, mPlayer.getId());

    }
    @Test
    public void playerGetId2(){
        long num = 1234789674739L;
        mPlayer = new Player(num);
        assertEquals(num, mPlayer.getId());

    }
    @Test
    public void playerGetNameKelly() {
        mPlayer = new Player(1);
        mPlayer.setFirst_name("Kelly");
        assertEquals("Kelly", mPlayer.getFirst_Name());
    }
    @Test
    public void playerGetNameEva() {
        mPlayer = new Player(1);
        mPlayer.setFirst_name("Eva");
        assertEquals("Eva", mPlayer.getFirst_Name());
    }
    @Test
    public void playerGetNameBlake() {
        mPlayer = new Player(1);
        mPlayer.setFirst_name("Blake");
        assertEquals("Blake", mPlayer.getFirst_Name());
    }
    @Test
    public void playerGetEmail1() {
        mPlayer = new Player(1);
        mPlayer.setEmail("test@osu.edu");
        assertEquals("test@osu.edu", mPlayer.getEmail());
    }
    @Test
    public void playerGetEmail2() {
        mPlayer = new Player(1);
        mPlayer.setEmail("test_email_matches@osu.edu");
        assertEquals("test_email_matches@osu.edu", mPlayer.getEmail());
    }
    @Test
    public void playerGetGenderFemale() {
        mPlayer = new Player(1);
        mPlayer.setGender("Female");
        assertEquals("Female", mPlayer.getGender());
    }
    @Test
    public void playerGetGenderMale() {
        mPlayer = new Player(1);
        mPlayer.setGender("Male");
        assertEquals("Male", mPlayer.getGender());
    }
}
