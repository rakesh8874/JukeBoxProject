package com.songdetails;
import com.connectionwithdatabase.ConnectWithDataBase;
import java.sql.*;
import java.util.*;
public class SongImpl extends ConnectWithDataBase {
    public List<Song> showAllSong() {
        List<Song> songsDetails = new ArrayList<>();
        try {
            String sql = "select * from songsDetails";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Song song;
            while(resultSet.next()){
                int s_id = resultSet.getInt(1);
                String s_title = resultSet.getString(2);
                String s_album = resultSet.getString(3);
                String s_genre = resultSet.getString(4);
                String s_author = resultSet.getString(5);
                String s_duration = resultSet.getString(6);
                song = new Song(s_id,s_title,s_album,s_genre,s_author,s_duration);
                songsDetails.add(song);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return songsDetails;
    }
    public int printDetails(List<Song> songsDetails){
        int row = 0;
        Iterator<Song> iterator = songsDetails.iterator();
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.print("Song ID    Song Title         Song Album Name       Song Genre           Song Artist        Song Duration");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------");
        while(iterator.hasNext()){
            Song details = iterator.next();
            System.out.format("%-10s %-20s %-20s %-20s %-20s %-10s %n",details.getSongId(),details.getTitle(),details.getAlbumName(),details.getSongGenre(),details.getArtist(),details.getSongDuration());
            row = 1;
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
        return row;
    }
    public String getSongPath(int songId){
        String s_path = "";
        try {
            String sql = "select SongPath from SongsDetails where SongId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, songId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                s_path = resultSet.getString(1);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return s_path;
    }
    public List<Song> searchSongByAlbumName(List<Song> allSongs,String albumName){
        List<Song> songAsPerAlbumName = new ArrayList<>();
        Iterator<Song> iterator = allSongs.iterator();
        while(iterator.hasNext()){
            Song songsAlbum = iterator.next();
            if(songsAlbum.getAlbumName().equalsIgnoreCase(albumName)){
                songAsPerAlbumName.add(songsAlbum);
            }
        }
        Comparator <Song>comparator = (o1,o2) ->o1.getTitle().compareToIgnoreCase(o2.getTitle());
        Collections.sort(songAsPerAlbumName, comparator);
        return songAsPerAlbumName;
    }
    public List<Song> searchSongByArtistName(List<Song> allSongs,String artistName){
        List<Song> songAsPerArtistName = new ArrayList<>();
        Iterator<Song> iterator = allSongs.iterator();
        while(iterator.hasNext()){
            Song songsAlbum = iterator.next();
            if(songsAlbum.getArtist().equalsIgnoreCase(artistName)){
                songAsPerArtistName.add(songsAlbum);
            }
        }
        Comparator <Song >comparator = (o1,o2) ->o1.getTitle().compareToIgnoreCase(o2.getTitle());
        Collections.sort(songAsPerArtistName, comparator);
        return songAsPerArtistName;
    }
    public List<Song> searchSongByGenre(List<Song> allSongs, String genreName){
        List<Song> searchByGenre = new ArrayList<>();
        Iterator<Song> iterator = allSongs.iterator();
        while(iterator.hasNext()){
            Song songsGenre = iterator.next();
            if(songsGenre.getSongGenre().equalsIgnoreCase(genreName)){
                searchByGenre.add(songsGenre);
            }
        }
        Comparator <Song>comparator = (o1,o2) ->o1.getTitle().compareToIgnoreCase(o2.getTitle());
        Collections.sort(searchByGenre, comparator);
        return searchByGenre;
    }
    public List<Song> searchSongByName(List<Song> songsDetails, String songName){
        List<Song> searchByName = new ArrayList<>();
        Iterator <Song> iterator = songsDetails.iterator();
        while(iterator.hasNext()){
            Song songsName = iterator.next();
            if(songsName.getTitle().equalsIgnoreCase(songName)){
                searchByName.add(songsName);
            }
        }
        return searchByName;
    }
    public List<Song> applyFilter(List<Song> songsDetails, String ch){
        List<Song> songsFilter = new ArrayList<>();
        for(Song songs:songsDetails){
            if(songs.getTitle().startsWith(ch)){
                songsFilter.add(songs);
            }
        }
        return songsFilter;
    }

}