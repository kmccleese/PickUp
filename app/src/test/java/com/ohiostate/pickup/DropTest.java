package com.ohiostate.pickup;

import android.content.Context;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DropTest {
    private Drop mDrop;

    @Test
    public void newDrop () throws Exception{
        mDrop = new Drop();

    }

    @Test
    public void dropGetLocation1() throws Exception {
        mDrop = new Drop();
        mDrop.setLocation("123 Test, City, State, 12345");
        assertEquals("123 Test, City, State, 12345", mDrop.getLocation());

    }
    @Test
    public void dropGetLocation2() throws Exception {
        mDrop = new Drop();
        mDrop.setLocation("1234 Highway 32 N, Columbus, OH, 43210");
        assertEquals("1234 Highway 32 N, Columbus, OH, 43210", mDrop.getLocation());

    }
    @Test
    public void dropGetGenderFemale() throws Exception {
        mDrop = new Drop();
        mDrop.setGender("Females only");
        assertEquals("Females only", mDrop.getGender());
    }
    @Test
    public void dropGetGenderMale() throws Exception {
        mDrop = new Drop();
        mDrop.setGender("Males only");
        assertEquals("Males only", mDrop.getGender());
    }
    @Test
    public void dropGetSport1() throws Exception {
        mDrop = new Drop();
        mDrop.setSport("Soccer");
        assertEquals("Soccer", mDrop.getSport());
    }
    @Test
    public void dropGetSport2() throws Exception {
        mDrop = new Drop();
        mDrop.setSport("Badminton");
        assertEquals("Badminton", mDrop.getSport());
    }
    @Test
    public void dropGetSkillLevelBeginner() throws Exception {
        mDrop = new Drop();
        mDrop.setDifficulty("Beginner player's only");
        assertEquals("Beginner player's only", mDrop.getDifficulty());
    }
    @Test
    public void dropGetSkillLevelIntermediate() throws Exception {
        mDrop = new Drop();
        mDrop.setDifficulty("Intermediate player's only");
        assertEquals("Intermediate player's only", mDrop.getDifficulty());
    }
    @Test
    public void dropGetSkillLevelAny() throws Exception {
        mDrop = new Drop();
        mDrop.setDifficulty("Any player's only");
        assertEquals("Any player's only", mDrop.getDifficulty());
    }
    @Test
    public void dropGetDate() throws Exception {
        mDrop = new Drop();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Date date = new GregorianCalendar(year, month, day).getTime();
        mDrop.setDate(date);
        assertEquals(date, mDrop.getDate());
    }
    @Test
    public void dropGetNumPlayers1() throws Exception {
        mDrop = new Drop();
        mDrop.setNum_players(1);
        assertEquals(1d, mDrop.getNum_players(),0);
    }

    @Test
    public void dropGetNumPlayers24() throws Exception {
        mDrop = new Drop();
        mDrop.setNum_players(24);
        assertEquals(24d, mDrop.getNum_players(),0);
    }
    @Test
    public void dropGetNumPlayers1000() throws Exception {
        mDrop = new Drop();
        mDrop.setNum_players(1000);
        assertEquals(1000d, mDrop.getNum_players(),0);
    }
    @Test
    public void dropGetMessageShort() throws Exception {
        mDrop = new Drop();
        mDrop.setMessage("Short msg");
        assertEquals("Short msg", mDrop.getMessage());
    }
    @Test
    public void dropGetMessageLong() throws Exception {
        mDrop = new Drop();
        String msg = "Testing a longer message than before, this is for testing purposes, checking that this message matches the one that is pulled from the drop on get drop";
        mDrop.setMessage(msg);
        assertEquals(msg, mDrop.getMessage());
    }

}