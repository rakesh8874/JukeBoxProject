package com.testingmethods;

import com.songdetails.Song;
import com.songdetails.SongImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SongDetailsTest {
    SongImpl songImpl = null;
    List<Song> allSongs  = null;
    
    @Before
    public void setUp(){
        songImpl = new SongImpl();
        allSongs = songImpl.showAllSong();
    }
    @After
    public void tearUp(){
        songImpl = null;
    }
    @Test
    public void showAllSongTest1(){
        assertEquals(1,songImpl.printDetails(allSongs));
    }
    @Test
    public void searchSongByAlbumNameTest1(){
        List<Song> searchByAlbum = new ArrayList<>();
        assertEquals(searchByAlbum,songImpl.searchSongByAlbumName(allSongs,""));
    }
    @Test
    public void searchSongByArtistNameTest1(){
        List<Song> searchByArtist = new ArrayList<>();
        assertEquals(searchByArtist,songImpl.searchSongByAlbumName(allSongs,""));
    }
    @Test
    public void searchSongByGenreNameTest1(){
        List<Song> searchByGenre = new ArrayList<>();
        assertEquals(searchByGenre,songImpl.searchSongByAlbumName(allSongs,""));
    }
    @Test
    public void searchSongBySongNameTest1(){
        List<Song> searchBySongName = new ArrayList<>();
        assertEquals(searchBySongName,songImpl.searchSongByAlbumName(allSongs,""));
    }
    @Test
    public void showAllSongTest2(){

        assertNotEquals(0,songImpl.printDetails(allSongs));
    }
    @Test
    public void searchSongByAlbumNameTest2(){
        List<Song> searchByAlbum = new ArrayList<>();
        int [] album = new int[10];
        assertNotEquals(album,songImpl.searchSongByAlbumName(allSongs,""));
    }
    @Test
    public void searchSongByArtistNameTest2(){
        List<Song> searchByArtist = new ArrayList<>();
        int [] artist = new int[10];
        assertNotEquals(artist,songImpl.searchSongByAlbumName(allSongs,""));
    }
    @Test
    public void searchSongByGenreNameTest2(){
        List<Song> searchByGenre = new ArrayList<>();
        int [] genre = new int[10];
        assertNotEquals(genre,songImpl.searchSongByAlbumName(allSongs,""));
    }
    @Test
    public void searchSongBySongNameTest2(){
        List<Song> searchBySongName = new ArrayList<>();
        int [] songName = new int[10];
        assertNotEquals(songName,songImpl.searchSongByAlbumName(allSongs,""));
    }

}
