package com.audioplayer;

import com.songdetails.Song;
import com.songdetails.SongImpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreatePlaylist {
    Connection connection;

    public  CreatePlaylist() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JukeBox", "root", "Rakesh@2022");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public int createPlaylist(String playListName, String userId) {
        int row = 0;
        try {
            String sql = "insert into usersplaylist (playListName,UserID) values(?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,playListName);
            statement.setString(2,userId);
            row = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return row;
    }
    public void showPlayListDetails(String userId) {
        try {
            String sql = "select * from usersplaylist where userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,userId);
            ResultSet resultSet = statement.executeQuery();
            System.out.print("PlaylistID    PlaylistName");
            System.out.println();
            System.out.println("=============================");
            while (resultSet.next()){
                String userEmailId = resultSet.getString(3);
                if(userId.equals(userEmailId)){
                    int playlistID = resultSet.getInt(1);
                    String plaListName = resultSet.getString(2);
                    System.out.println(playlistID+"         "+plaListName);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addSongToPlayList(int playListId, int songId) {
        int playId = 0;
        SongImpl song = new SongImpl();
        List<Song> addIntoPlaylist = new ArrayList<>();
        List<Song> songsDetails = song.showAllSong();
        try {
            String sql = "select UPlayId from usersPlaylistDetails";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,playListId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                playId = resultSet.getInt(1);
                if(playListId==playId){
                    System.out.println("Selected Song Has Been Added Successfully Into Your Playlist");
                }else{
                    System.out.println("Error While Adding Song Try Again");
                }
            }
            Iterator<Song> iterator = songsDetails.iterator();
            while(iterator.hasNext()){
                Song song1 = iterator.next();
                if(song1.getSongId() == songId){
                    addIntoPlaylist.add(song1);
                }
            }
            for(Song plDetails:addIntoPlaylist){
                System.out.println(plDetails);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

