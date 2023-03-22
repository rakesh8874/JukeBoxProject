package com.users;

public class Song {
    private int songId;
    private String title;
    private String albumName;
    private String songGenre;
    private String artist;
    private String songDuration;
    private String songPath;

    public Song(int songId, String title, String albumName, String songGenre, String artist, String songDuration, String songPath) {
        this.songId = songId;
        this.title = title;
        this.albumName = albumName;
        this.songGenre = songGenre;
        this.artist = artist;
        this.songDuration = songDuration;
        this.songPath = songPath;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

//    @Override
//    public String toString() {
//        return   "Song Details : SongID : "+songId+" Song Title :"+title+" Album Name : "+albumName+
//                " Song Genre : "+songGenre+" Artist : "+artist+" Song Duration : "+songDuration+"\n==================================================================================================================";
//    }
}

