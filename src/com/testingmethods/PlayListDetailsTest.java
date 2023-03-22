package com.testingmethods;

import com.playlist.CreatePlaylist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class PlayListDetailsTest{
    CreatePlaylist createPlaylist = null;
    @Before
    public  void setUp(){
        createPlaylist = new CreatePlaylist();
    }
    @After
    public  void tearDown(){
        createPlaylist = null;
    }
    @Test
    public void createPlaylistTest1(){
        assertEquals(1,createPlaylist.createPlaylist("ABC","abhi123@gmail.com"));
    }
    @Test
    public void addSongPlaylistTest1(){
        assertEquals(1,createPlaylist.addSongToPlayList(22,102));
    }
    @Test
    public void showPlaylistDetailsTest1(){
        assertEquals(1,createPlaylist.showPlaylistDetails(19));
    }
    @Test
    public void createPlaylistTest2(){
        assertNotEquals(1,createPlaylist.createPlaylist("xyz","sudheer123@gmail.com"));
    }
    @Test
    public void addSongPlaylistTest2(){
        assertNotEquals(1,createPlaylist.addSongToPlayList(45,110));
    }
    @Test
    public void showPlaylistDetailsTest2(){
        assertNotEquals(1,createPlaylist.showPlaylistDetails(45));
    }
}
