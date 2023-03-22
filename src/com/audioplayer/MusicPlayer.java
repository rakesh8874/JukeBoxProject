package com.audioplayer;
import com.playlist.CreatePlaylist;
import com.songdetails.Song;
import com.songdetails.SongImpl;
import com.users.UsersData;
import java.util.*;
public class MusicPlayer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsersData usersData = new UsersData();
        SongImpl songImpl = new SongImpl();
        List<Song> allSong = new ArrayList<>();
        List<Integer> playListId1 = new ArrayList<>();
        CreatePlaylist createPlaylist = new CreatePlaylist();
        boolean i = true;
        String songPath = "";
        int isSong = 0;
        try {
            while (i) {
                System.out.println("------------------------------------------------------\nPlease Select From Below Options Which is 1 or 2 or 3\n------------------------------------------------------\n 1 -> SignIn \n 2 -> Login \n 3 -> Exit \n------------------------------------------------------");
                int isChoice = scanner.nextInt();
                switch (isChoice) {
                    case 1:
                        System.out.println("-----------------------------------");
                        System.out.println("Please Provide Details To Sign in : ");
                        System.out.println("-----------------------------------");
                        System.out.println("Please Provide Your First Name : ");
                        String usersFirstName = scanner.nextLine();
                        usersFirstName = scanner.nextLine();
                        String userFirstName = usersFirstName.substring(0,1).toUpperCase().concat(usersFirstName.substring(1));
                        System.out.println("Please Provide Your Last Name : ");
                        String usersLastName = scanner.nextLine();
                        String userLastName = usersLastName.substring(0,1).toUpperCase().concat(usersLastName.substring(1));
                        System.out.println("Please Enter Your Contact Number : ");
                        long userPhoneNumber = scanner.nextLong();
                        System.out.println("Please Provide Your Email ID Which You Will use While Login :");
                        String userEmail = scanner.nextLine();
                        userEmail = scanner.nextLine();
                        System.out.println("Please Set Your Login Password Insuring All Below Conditions :\n--------------------------------------------------------------\nIt contains at least 6 characters and at most 12 characters.\n" +
                                "It contains at least one digit.\n" +
                                "It contains at least one upper case alphabet.\n" +
                                "It contains at least one lower case alphabet.\n" +
                                "It contains at least one special character which includes !@#$%&*()-+=^.\n" +
                                "It does’t contain any white space.\n--------------------------------------------------------------");
                        String userPass = scanner.nextLine();
                        int signInMessage = usersData.userSignIn(userFirstName, userLastName, userPhoneNumber, userEmail, userPass);
                            if (signInMessage == 1) {
                                System.out.println("Hi " + userFirstName + " "+userLastName+ " You Have Been Signed In Successfully Please Login in Our Portal");
                            } else {
                                break;
                            }
                        System.out.println("----------------------------------------------");
                        System.out.println("Please Provide Details To Login In Our Portal : ");
                        System.out.println("----------------------------------------------");
                        System.out.println("Please Provide Your Valid Email ID : ");
                        String userEmailId = scanner.next();
                        System.out.println("Please Provide Your Valid Password : ");
                        String userPassword = scanner.next();
                        int login = usersData.userLoginPage(userEmailId, userPassword);
                        boolean j = true;
                        if (login == 1) {
                            System.out.println("---------------------------------------");
                            System.out.println("Welcome " + userEmailId);
                            System.out.println("---------------------------------------");
                            while (j) {
                                System.out.println("-------------------------------------------------------------");
                                System.out.println("Please Select From Given Below Options \nWhich can be 1 or 2 or 3 or 4 or 5 or 6 or 7 or 8 or 9 or 10");
                                System.out.println("-------------------------------------------------------------");
                                System.out.println("1 > All Songs ");
                                System.out.println("2 > Apply Filter A to Z");
                                System.out.println("3 > Create Playlist");
                                System.out.println("4 > Add Item To PlayList");
                                System.out.println("5 > See All Your Available Playlist");
                                System.out.println("6 > Search Song By Artist Name");
                                System.out.println("7 > Search Song By Album Name");
                                System.out.println("8 > Search Song By Genre");
                                System.out.println("9 > Search Song By Name");
                                System.out.println("10 > Exit");
                                System.out.println("-------------------------------------------------------------");
                                int isCh = scanner.nextInt();
                                switch (isCh) {
                                    case 1:
                                        allSong = songImpl.showAllSong();
                                        songImpl.printDetails(allSong);
                                        System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                        isSong = scanner.nextInt();
                                        songPath = songImpl.getSongPath(isSong);
                                        PlayMusic.streamAudio(songPath);
                                        break;
                                    case 2:
                                        System.out.println("Please Provide Character Between A to Z to Apply Filter");
                                        String ch = scanner.next();
                                        String character = ch.toUpperCase();
                                        List<Song> filteredSong= songImpl.applyFilter(allSong,character);
                                        int isAvailable =  songImpl.printDetails(filteredSong);
                                        if(isAvailable==1){
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            int songId = scanner.nextInt();
                                            songPath = songImpl.getSongPath(songId);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Any Available Songs on Given Record \n You Can Search For Another Song Or Choose All Songs");
                                        }
                                        break;
                                        case 3:
                                        boolean isCh1 = true;
                                        while(isCh1) {
                                            System.out.println("---------------------------------");
                                            System.out.println("Please Select From 1 or 0");
                                            System.out.println("---------------------------------");
                                            System.out.println("1 -> Create Your Playlist");
                                            System.out.println("0 -> Exit");
                                            System.out.println("---------------------------------");
                                            int option = scanner.nextInt();
                                            switch (option){
                                                case 1:
                                                    System.out.println("Please Provide Playlist Name");
                                                    String playListName = scanner.nextLine();
                                                    playListName = scanner.nextLine();
                                                    int res = createPlaylist.createPlaylist(playListName, userEmailId);
                                                    if (res == 1) {
                                                        System.out.println("Your Playlist Has Been Created Successfully");
                                                    }
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    int isSongExist = 0;
                                                    int addSong = 0;
                                                    allSong = songImpl.showAllSong();
                                                    System.out.println("Add Songs Into Your Playlist Choose Playlist ID or Choose 0 to Exit ");
                                                    int playListId = scanner.nextInt();
                                                    playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                      for(Integer element1:playListId1)
                                                          if(element1==playListId) {
                                                              System.out.println("Search Songs To Add Into Playlist By Song Name");
                                                              String searchSong = scanner.nextLine();
                                                              searchSong = scanner.nextLine();
                                                              List<Song> searchByName = songImpl.searchSongByName(allSong, searchSong);
                                                              isSongExist = songImpl.printDetails(searchByName);
                                                              if (isSongExist == 1) {
                                                                  System.out.println("Please Choose Song Which One You Want to Add In Your Playlist Using Song Id Provided Above");
                                                                  int songId = scanner.nextInt();
                                                                  playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                                  for (Integer element : playListId1) {
                                                                      if (playListId == element) {
                                                                          addSong = createPlaylist.addSongToPlayList(playListId, songId);
                                                                      }
                                                                  }
                                                                  if (addSong == 1) {
                                                                      System.out.println("Song Added Successfully Into Your Selected Playlist");
                                                                      createPlaylist.showPlaylistDetails(playListId);
                                                                      System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                                      isSong = scanner.nextInt();
                                                                      songPath = songImpl.getSongPath(isSong);
                                                                      PlayMusic.streamAudio(songPath);
                                                                  } else {
                                                                      System.out.println("Might Be Playlist Id or Song ID is Incorrect");
                                                                  }
                                                              } else {
                                                                  System.out.println("Sorry! We Don't Have Available Song Of This Name");
                                                              }
                                                          }
                                                      break;
                                                    default:
                                                    isCh1 = false;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 4:
                                        boolean isCh2 = true;
                                        while(isCh2) {
                                            System.out.println("---------------------------");
                                            System.out.println("Available Playlist You Have");
                                            createPlaylist.showPlayListDetails(userEmailId);
                                            System.out.println("---------------------------------");
                                            System.out.println("Please Select From 1 or 0");
                                            System.out.println("---------------------------------");
                                            System.out.println("1 -> Add Item into Playlist");
                                            System.out.println("0 -> Exit");
                                            System.out.println("---------------------------------");
                                            int option1 = scanner.nextInt();
                                            switch (option1) {
                                                case 1:
                                                    int isSongExist = 0;
                                                    int addSong = 0;
                                                    allSong = songImpl.showAllSong();
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Choose Your Playlist in Which You Want to Add Song By Playlist Id Provided Above");
                                                    int playListId = scanner.nextInt();
                                                    System.out.println("Search Songs To Add Into Playlist By Song Name");
                                                    String searchSong  = scanner.nextLine();
                                                    searchSong = scanner.nextLine();
                                                    List<Song> searchByName = songImpl.searchSongByName(allSong, searchSong);
                                                            isSongExist = songImpl.printDetails(searchByName);
                                                            if(isSongExist==1){
                                                        System.out.println("Please Choose Song Which One You Want to Add In Your Playlist Using Song Id Provided Above");
                                                        int songId = scanner.nextInt();
                                                        playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                        for(Integer element:playListId1) {
                                                            if (playListId == element) {
                                                                addSong = createPlaylist.addSongToPlayList(playListId, songId);
                                                            }
                                                        }
                                                        if(addSong==1) {
                                                            System.out.println("Song Added Successfully Into Your Selected Playlist");
                                                            createPlaylist.showPlaylistDetails(playListId);
                                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                            isSong = scanner.nextInt();
                                                            songPath = songImpl.getSongPath(isSong);
                                                            PlayMusic.streamAudio(songPath);
                                                        }else{
                                                            System.out.println("Might Be Playlist Id or Song ID is Incorrect");
                                                        }
                                                    }else{
                                                        System.out.println("Sorry! We Don't Have Available Song Of This Name");
                                                    }
                                                    break;
                                                    default:
                                                    isCh2 = false;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 5:
                                        boolean isCh3 = true;
                                        while(isCh3) {
                                            System.out.println("---------------------------");
                                            System.out.println("Available Playlist You Have");
                                            System.out.println("---------------------------");
                                            createPlaylist.showPlayListDetails(userEmailId);
                                            System.out.println("---------------------------------");
                                            System.out.println("Please Select From 1 or 2 or 3");
                                            System.out.println("---------------------------------");
                                            System.out.println("1 -> Check Your Playlist Item");
                                            System.out.println("2 -> Add Item Into Available Playlist");
                                            System.out.println("3 -> Delete Item From Playlist");
                                            System.out.println("4 -> Delete Your Empty Playlist");
                                            System.out.println("5 -> Exit");
                                            System.out.println("---------------------------------");
                                            int option2 = scanner.nextInt();
                                            switch (option2) {
                                                case 1:
                                                    int isAvailable1 = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Provide Playlist ID To Check Your Playlist Details");
                                                    int playlistId = scanner.nextInt();
                                                    playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                    for(Integer element:playListId1) {
                                                        if (playlistId == element) {
                                                            isAvailable1 = createPlaylist.showPlaylistDetails(playlistId);
                                                        }
                                                    }
                                                    if (isAvailable1 == 1) {
                                                        System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                        isSong = scanner.nextInt();
                                                        songPath = songImpl.getSongPath(isSong);
                                                        PlayMusic.streamAudio(songPath);
                                                    }else{
                                                        System.out.println("Might be Playlist isn't Available Which Playlist Id You Have Provided or \nYou haven't added any song into your playlist");
                                                    }
                                                    break;
                                                case 2:
                                                    int isSongExist = 0;
                                                    String searchSong = "";
                                                    int addSong = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    allSong = songImpl.showAllSong();
                                                    System.out.println("Please Choose Your Playlist in Which You Want to Add Songs By Playlist Id Provided Above");
                                                    int playListId = scanner.nextInt();
                                                    System.out.println("Search Songs To Add Into Playlist By Song Name");
                                                    searchSong = scanner.nextLine();
                                                    searchSong = scanner.nextLine();
                                                    List<Song> searchByName = songImpl.searchSongByName(allSong, searchSong);
                                                    isSongExist = songImpl.printDetails(searchByName);
                                                    if(isSongExist==1){
                                                        System.out.println("Please Choose Song Which One You Want to Add In Your Playlist Using Song Id Provided Above");
                                                        int songId = scanner.nextInt();
                                                        playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                        for(Integer element:playListId1) {
                                                            if (playListId == element) {
                                                                addSong = createPlaylist.addSongToPlayList(playListId, songId);
                                                            }
                                                        }
                                                        if(addSong==1) {
                                                            System.out.println("Song Added Successfully Into Your Selected Playlist");
                                                            createPlaylist.showPlaylistDetails(playListId);
                                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                            isSong = scanner.nextInt();
                                                            songPath = songImpl.getSongPath(isSong);
                                                            PlayMusic.streamAudio(songPath);
                                                        }else{
                                                            System.out.println("Might Be Playlist Id or Song ID is Incorrect");
                                                        }
                                                    }
                                                    break;
                                                case 3:
                                                    int showPlaylist = 0;
                                                    int deleted = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Select Playlist Id To Delete Item");
                                                    int playListID = scanner.nextInt();
                                                    playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                    for(Integer element:playListId1) {
                                                        if (playListID == element) {
                                                            showPlaylist = createPlaylist.showPlaylistDetails(playListID);
                                                        }
                                                    }
                                                    if(showPlaylist==1) {
                                                        System.out.println("Please Provide Song Id Which One You Want to Delete");
                                                        int songId = scanner.nextInt();
                                                        playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                        for(Integer element:playListId1) {
                                                            if (playListID == element) {
                                                                deleted = createPlaylist.deleteSongsFromPlaylist(playListID, songId);
                                                            }
                                                        }
                                                        if (deleted == 1) {
                                                            System.out.println("Item Deleted Successfully From Playlist");
                                                        } else{
                                                            System.out.println("Failed To Do Operations May be Your Playlist is Empty or Wrong Playlist ID Chosen");
                                                        }
                                                    }else{
                                                        System.out.println("Failed To Do Operations May be Your Playlist is Empty or Wrong Playlist ID Chosen");
                                                    }
                                                    break;
                                                case 4:
                                                    int deletePlaylists = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Select Playlist Id To Delete");
                                                    int playList = scanner.nextInt();
                                                    for(Integer element:playListId1) {
                                                        if (playList == element) {
                                                            deletePlaylists = createPlaylist.deletePlaylist(playList);
                                                        }
                                                    }

                                                    if(deletePlaylists>0){
                                                        System.out.println("Playlist Deleted Successfully");
                                                    }else{
                                                        System.out.println("Wrong Playlist ID Chosen");
                                                    }
                                                    break;
                                                    default:
                                                    isCh3 = false;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 6:
                                        System.out.println("Provide Artist Name : ");
                                        String artistName = scanner.nextLine();
                                        artistName = scanner.nextLine();
                                        List<Song> searchByArtistName = songImpl.searchSongByArtistName(allSong, artistName);
                                        int isExist = songImpl.printDetails(searchByArtistName);
                                        if (isExist == 1) {
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            isSong = scanner.nextInt();
                                            songPath = songImpl.getSongPath(isSong);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Available Songs Of Given Artist Name\n You Can Search For Another Artist Or Choose All Songs");
                                        }
                                        break;
                                    case 7:
                                        System.out.println("Provide Album Name : ");
                                        String albumName = scanner.nextLine();
                                        albumName = scanner.nextLine();
                                        List<Song> searchByAlbumName = songImpl.searchSongByAlbumName(allSong, albumName);
                                        int isExist2 = songImpl.printDetails(searchByAlbumName);
                                        if (isExist2 == 1) {
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            isSong = scanner.nextInt();
                                            songPath = songImpl.getSongPath(isSong);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Available Songs Of Given Album Name\n You Can Search For Another Album Or Choose All Songs");
                                        }
                                        break;
                                    case 8:
                                        System.out.println("Provide Song Genre : ");
                                        String genreName = scanner.nextLine();
                                        genreName = scanner.nextLine();
                                        List<Song> searchByGenre = songImpl.searchSongByGenre(allSong, genreName);
                                        int isExist1 = songImpl.printDetails(searchByGenre);
                                        if (isExist1 == 1) {
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            isSong = scanner.nextInt();
                                            songPath = songImpl.getSongPath(isSong);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Available Songs Of Given Genre Name\n You Can Search For Another Genre Or Choose All Songs");
                                        }
                                        break;
                                    case 9:
                                        System.out.println("Provide Song Name: ");
                                        String songName = scanner.nextLine();
                                        songName = scanner.nextLine();
                                        List<Song> searchByName = songImpl.searchSongByName(allSong, songName);
                                        int isExist3 = songImpl.printDetails(searchByName);
                                        if (isExist3 == 1) {
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            isSong = scanner.nextInt();
                                            songPath = songImpl.getSongPath(isSong);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Available Given Songs\n You Can Search For Another Song Or Choose All Songs");
                                        }
                                        break;
                                    default:
                                        j = false;
                                        break;
                                }
                            }
                        }
                        else {
                            System.out.println("You Can Try Again, Details Are Not Adequate");
                        }
                        break;
                    case 2:
                        System.out.println("----------------------------------------------");
                        System.out.println("Please Provide Details To Login In Our Portal : ");
                        System.out.println("----------------------------------------------");
                        System.out.println("Please Provide Your Valid Email Id : ");
                        userEmailId = scanner.next();
                        System.out.println("Please Provide Your Valid Password : ");
                        userPassword = scanner.next();
                        login = usersData.userLoginPage(userEmailId, userPassword);
                        boolean k = true;
                        if (login == 1) {
                            System.out.println("---------------------------------------");
                            System.out.println("Welcome " + userEmailId);
                            System.out.println("---------------------------------------");
                            while (k) {
                                System.out.println("-------------------------------------------------------------");
                                System.out.println("Please Select From Given Below Options \nWhich can be 1 or 2 or 3 or 4 or 5 or 6 or 7 or 8 or 9 or 10");
                                System.out.println("-------------------------------------------------------------");
                                System.out.println("1 > All Songs ");
                                System.out.println("2 > Apply Filter A to Z");
                                System.out.println("3 > Create Playlist");
                                System.out.println("4 > Add Item To PlayList");
                                System.out.println("5 > See All Your Available Playlist");
                                System.out.println("6 > Search Song By Artist Name");
                                System.out.println("7 > Search Song By Album Name");
                                System.out.println("8 > Search Song By Genre");
                                System.out.println("9 > Search Song By Name");
                                System.out.println("10 > Exit");
                                System.out.println("-------------------------------------------------------------");
                                int isCh = scanner.nextInt();
                                switch (isCh) {
                                    case 1:
                                        allSong = songImpl.showAllSong();
                                        songImpl.printDetails(allSong);
                                        System.out.println("Select Song to Listen By Using Song ID or Choose 0 To Exit");
                                        isSong = scanner.nextInt();
                                        songPath = songImpl.getSongPath(isSong);
                                        PlayMusic.streamAudio(songPath);
                                        break;
                                    case 2:
                                        System.out.println("Please Provide Character Between A to Z to Apply Filter");
                                        String ch = scanner.next();
                                        String character = ch.toUpperCase();
                                        List<Song> filteredSong= songImpl.applyFilter(allSong,character);
                                        int isAvailable =  songImpl.printDetails(filteredSong);
                                        if(isAvailable==1){
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            int songId = scanner.nextInt();
                                            songPath = songImpl.getSongPath(songId);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Any Available Songs on Given Record \n You Can Search For Another Song Or Choose All Songs");
                                        }
                                        break;
                                    case 3:
                                        boolean isCh1 = true;
                                        while(isCh1) {
                                            System.out.println("---------------------------------");
                                        System.out.println("Please Select From 1 or 2 or 3");
                                        System.out.println("---------------------------------");
                                        System.out.println("1 -> Create New Playlist");
                                        System.out.println("2 -> Use Your Available Playlist");
                                        System.out.println("3 -> Exit");
                                        System.out.println("---------------------------------");
                                        int option = scanner.nextInt();
                                            switch (option){
                                                case 1:
                                                    int isSongExist = 0;
                                                    int playListId = 0;
                                                    int addSong = 0;
                                                System.out.println("Please Provide Playlist Name");
                                                String playListName = scanner.nextLine();
                                                playListName = scanner.nextLine();
                                                int res = createPlaylist.createPlaylist(playListName, userEmailId);
                                                if (res == 1) {
                                                    System.out.println("Your Playlist Has Been Created Successfully");
                                                }
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Add Songs Into Your Playlist Choose Playlist ID or Choose 0 To Exit");
                                                    int playlistId2 = scanner.nextInt();
                                                    playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                     for(Integer element:playListId1)
                                                              if(playlistId2==element){
                                                                  System.out.println("Search Songs To Add Into Playlist By Song Name");
                                                                  String searchSong = scanner.nextLine();
                                                                  searchSong = scanner.nextLine();
                                                                  List<Song> searchByName = songImpl.searchSongByName(allSong, searchSong);
                                                                  isSongExist = songImpl.printDetails(searchByName);
                                                                  if (isSongExist == 1) {
                                                                      System.out.println("Please Choose Song Which One You Want to Add In Your Playlist Using Song Id Provided Above");
                                                                      int songId = scanner.nextInt();
                                                                      playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                                      for (Integer element1 : playListId1) {
                                                                          if (playlistId2 == element1) {
                                                                              addSong = createPlaylist.addSongToPlayList(playlistId2, songId);
                                                                          }
                                                                      }
                                                                      if (addSong == 1) {
                                                                          System.out.println("Song Added Successfully Into Your Selected Playlist");
                                                                          createPlaylist.showPlaylistDetails(playlistId2);
                                                                          System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                                          isSong = scanner.nextInt();
                                                                          songPath = songImpl.getSongPath(isSong);
                                                                          PlayMusic.streamAudio(songPath);
                                                                      } else {
                                                                          System.out.println("Might Be Playlist Id or Song ID is Incorrect");
                                                                      }
                                                                  } else {
                                                                      System.out.println("Sorry! We Don't Have Available Song Of This Name");
                                                                  }
                                                              }
                                                 break;
                                                 case 2:
                                                    int isAvailable1 = 0;
                                                     int playlistId = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Provide Playlist Id To see Available Playlist Item");
                                                    playlistId = scanner.nextInt();
                                                    playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                    for(Integer element:playListId1) {
                                                        if (playlistId == element) {
                                                            isAvailable1 = createPlaylist.showPlaylistDetails(playlistId);
                                                        }
                                                    }
                                                    if (isAvailable1 == 1) {
                                                        System.out.println("Select Song to Listen By Using Song ID Or Press 0 To Exit");
                                                        isSong = scanner.nextInt();
                                                        songPath = songImpl.getSongPath(isSong);
                                                        PlayMusic.streamAudio(songPath);
                                                    }else{
                                                        System.out.println("Might be Playlist isn't Available Which Playlist Id You Have Provided or \nYou haven't added any song into your playlist");
                                                    }
                                                break;
                                                default:
                                                    isCh1 = false;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 4:
                                        boolean isCh2 = true;
                                        while(isCh2) {
                                            System.out.println("---------------------------");
                                            System.out.println("Available Playlist You Have");
                                            createPlaylist.showPlayListDetails(userEmailId);
                                            System.out.println("---------------------------------");
                                            System.out.println("Please Select From 1 or 2 or 3");
                                            System.out.println("---------------------------------");
                                            System.out.println("1 -> Add Item into Playlist");
                                            System.out.println("2 -> Check Your Playlist Item Which is Already Available");
                                            System.out.println("3 -> Exit");
                                            System.out.println("---------------------------------");
                                            int option1 = scanner.nextInt();
                                            switch (option1) {
                                                case 1:
                                                    int addSong = 0;
                                                    int isSongExist = 0;
                                                    allSong = songImpl.showAllSong();
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Choose Your Playlist in Which You Want to Add Songs By Playlist Id Provided Above");
                                                    int playListId = scanner.nextInt();
                                                    System.out.println("Search Songs To Add Into Playlist By Song Name");
                                                    String searchSong= scanner.nextLine();
                                                    searchSong = scanner.nextLine();
                                                    List<Song> searchByName = songImpl.searchSongByName(allSong, searchSong);
                                                    isSongExist = songImpl.printDetails(searchByName);
                                                    if(isSongExist==1){
                                                        System.out.println("Please Choose Song Which One You Want to Add In Your Playlist Using Song Id Provided Above");
                                                        int songId = scanner.nextInt();
                                                        playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                        for(Integer element:playListId1) {
                                                            if (playListId == element) {
                                                                addSong = createPlaylist.addSongToPlayList(playListId, songId);
                                                            }
                                                        }
                                                        if(addSong==1) {
                                                            System.out.println("Song Added Successfully Into Your Selected Playlist");
                                                            createPlaylist.showPlaylistDetails(playListId);
                                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                            isSong = scanner.nextInt();
                                                            songPath = songImpl.getSongPath(isSong);
                                                            PlayMusic.streamAudio(songPath);
                                                        }else{
                                                            System.out.println("Might Be Playlist Id or Song ID is Incorrect");
                                                        }
                                                    }else{
                                                        System.out.println("Sorry! We Don't Have Available Song Of This Name");
                                                    }
                                                    break;
                                                case 2:
                                                    int isAvailable1 = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Provide Playlist ID To Check Your Playlist Details");
                                                    int playlistId = scanner.nextInt();
                                                    playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                    for(Integer element:playListId1) {
                                                        if (playlistId == element) {
                                                            isAvailable1 = createPlaylist.showPlaylistDetails(playlistId);
                                                        }
                                                    }
                                                    if (isAvailable1 == 1) {
                                                        System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                        isSong = scanner.nextInt();
                                                        songPath = songImpl.getSongPath(isSong);
                                                        PlayMusic.streamAudio(songPath);
                                                    }else{
                                                        System.out.println("Might be Playlist isn't Available Which Playlist Id You Have Provided or \nYou haven't added any song into your playlist");
                                                    }
                                                    break;
                                                default:
                                                    isCh2 = false;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 5:
                                        boolean isCh3 = true;
                                        while(isCh3) {
                                            System.out.println("---------------------------");
                                            System.out.println("Available Playlist You Have");
                                            System.out.println("---------------------------");
                                            createPlaylist.showPlayListDetails(userEmailId);
                                            System.out.println("---------------------------------");
                                            System.out.println("Please Select From 1 or 2 or 3");
                                            System.out.println("---------------------------------");
                                            System.out.println("1 -> Check Your Playlist Item");
                                            System.out.println("2 -> Add Item Into Available Playlist");
                                            System.out.println("3 -> Delete Items From Playlist");
                                            System.out.println("4 -> Delete Your Empty Playlist");
                                            System.out.println("5 -> Exit");
                                            System.out.println("---------------------------------");
                                            int option2 = scanner.nextInt();
                                            switch (option2) {
                                                case 1:
                                                    int isAvailable1 = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                            System.out.println("Please Provide Playlist ID To Check Your Playlist Details");
                                                            int playlistId = scanner.nextInt();
                                                            playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                            for(Integer element:playListId1) {
                                                                if (playlistId == element) {
                                                                    isAvailable1 = createPlaylist.showPlaylistDetails(playlistId);
                                                                }
                                                            }
                                                        if (isAvailable1 == 1) {
                                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                            isSong = scanner.nextInt();
                                                            songPath = songImpl.getSongPath(isSong);
                                                            PlayMusic.streamAudio(songPath);
                                                        }else{
                                                            System.out.println("Might be Playlist isn't Available Which Playlist Id You Have Provided or \nYou haven't added any song into your playlist");
                                                        }
                                                        break;
                                                case 2:
                                                    int addSong = 0;
                                                    int isSongExist = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    allSong = songImpl.showAllSong();
                                                    System.out.println("Please Choose Your Playlist in Which You Want to Add Songs By Playlist Id Provided Above");
                                                    int playListId = scanner.nextInt();
                                                    System.out.println("Search Songs To Add Into Playlist By Song Name");
                                                    String searchSong = scanner.nextLine();
                                                    searchSong = scanner.nextLine();
                                                    List<Song> searchByName = songImpl.searchSongByName(allSong, searchSong);
                                                    isSongExist = songImpl.printDetails(searchByName);
                                                    if(isSongExist==1){
                                                        System.out.println("Please Choose Song Which One You Want to Add In Your Playlist Using Song Id Provided Above");
                                                        int songId = scanner.nextInt();
                                                        playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                        for(Integer element:playListId1) {
                                                            if (playListId == element) {
                                                                addSong = createPlaylist.addSongToPlayList(playListId, songId);
                                                            }
                                                        }
                                                        if(addSong==1) {
                                                            System.out.println("Song Added Successfully Into Your Selected Playlist");
                                                            createPlaylist.showPlaylistDetails(playListId);
                                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                                            isSong = scanner.nextInt();
                                                            songPath = songImpl.getSongPath(isSong);
                                                            PlayMusic.streamAudio(songPath);
                                                        }else{
                                                            System.out.println("Might Be Playlist Id or Song ID is Incorrect");
                                                        }
                                                    }else{
                                                        System.out.println("Sorry! We Don't Have Available Song of This Name");
                                                    }
                                                    break;
                                                case 3:
                                                    int deleted = 0;
                                                    int showPlaylist = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Select Playlist Id To Delete Item");
                                                    int playListID = scanner.nextInt();
                                                    playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                    for(Integer element:playListId1) {
                                                        if (playListID == element) {
                                                            showPlaylist = createPlaylist.showPlaylistDetails(playListID);
                                                        }
                                                    }
                                                    if(showPlaylist==1) {
                                                       System.out.println("Please Provide Song Id Which One You Want to Delete");
                                                       int songId = scanner.nextInt();
                                                       playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                       for(Integer element:playListId1) {
                                                           if (playListID == element) {
                                                               deleted = createPlaylist.deleteSongsFromPlaylist(playListID, songId);
                                                           }
                                                       }
                                                       if (deleted == 1) {
                                                           System.out.println("Item Deleted Successfully From Selected Playlist");
                                                       } else {
                                                           System.out.println("Failed To Do Operations May be Your Playlist is Empty or Wrong Playlist ID Chosen");
                                                       }
                                                   }else{
                                                       System.out.println("Failed To Do Operations May be Your Playlist is Empty or Wrong Playlist ID Chosen");
                                                   }
                                                    break;
                                                case 4:
                                                    int deletePlaylists = 0;
                                                    System.out.println("---------------------------");
                                                    System.out.println("Available Playlist You Have");
                                                    System.out.println("---------------------------");
                                                    createPlaylist.showPlayListDetails(userEmailId);
                                                    System.out.println("---------------------------------");
                                                    System.out.println("Please Select Playlist Id To Delete");
                                                    int playList = scanner.nextInt();
                                                    playListId1 = createPlaylist.getAllPlayListId(userEmailId);
                                                    for(Integer element:playListId1) {
                                                        if (playList == element) {
                                                            deletePlaylists = createPlaylist.deletePlaylist(playList);
                                                        }
                                                    }

                                                    if(deletePlaylists>0){
                                                        System.out.println("Playlist Deleted Successfully");
                                                    }else{
                                                        System.out.println("Wrong Playlist Id Chosen");
                                                    }
                                                    break;
                                                    default:
                                                    isCh3 = false;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 6:
                                        System.out.println("Provide Artist Name : ");
                                        String artistName = scanner.nextLine();
                                        artistName = scanner.nextLine();
                                        List<Song> searchByArtistName = songImpl.searchSongByArtistName(allSong, artistName);
                                        int isExist = songImpl.printDetails(searchByArtistName);
                                        if (isExist == 1) {
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            isSong = scanner.nextInt();
                                            songPath = songImpl.getSongPath(isSong);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Available Songs Of Given Artist Name\n You Can Search For Another Artist Or Choose All Songs");
                                        }
                                        break;
                                    case 7:
                                        System.out.println("Provide Album Name : ");
                                        String albumName = scanner.nextLine();
                                        albumName = scanner.nextLine();
                                        List<Song> searchByAlbumName = songImpl.searchSongByAlbumName(allSong, albumName);
                                        int isExist1 = songImpl.printDetails(searchByAlbumName);
                                        if (isExist1 == 1) {
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            isSong = scanner.nextInt();
                                            songPath = songImpl.getSongPath(isSong);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Available Songs Of Given Album Name\n You Can Search For Another Album Or Choose All Songs");
                                        }
                                        break;
                                    case 8:
                                        System.out.println("Provide Song Genre : ");
                                        String genreName = scanner.nextLine();
                                        genreName = scanner.nextLine();
                                        List<Song> searchByGenre = songImpl.searchSongByGenre(allSong, genreName);
                                        int isExist2 = songImpl.printDetails(searchByGenre);
                                        if (isExist2 == 1) {
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            isSong = scanner.nextInt();
                                            songPath = songImpl.getSongPath(isSong);
                                            PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Available Songs Of Given Genre Name\n You Can Search For Another Genre Or Choose All Songs");
                                        }
                                        break;
                                    case 9:
                                        System.out.println("Provide Song Name: ");
                                        String songName = scanner.nextLine();
                                        songName = scanner.nextLine();
                                        List<Song> searchByName = songImpl.searchSongByName(allSong, songName);
                                        int isExist3 = songImpl.printDetails(searchByName);
                                        if (isExist3 == 1) {
                                            System.out.println("Select Song to Listen By Using Song ID or Press 0 To Exit");
                                            isSong = scanner.nextInt();
                                            songPath = songImpl.getSongPath(isSong);
                                                    PlayMusic.streamAudio(songPath);
                                        } else {
                                            System.out.println("Sorry! We Don't Have Available, Given Songs details \n You Can Search For Another Song Or Choose All Songs");
                                        }
                                        break;
                                        default:
                                        k = false;
                                        break;
                                }
                            }
                        } else {
                            System.out.println("You Can Try Again, Details Are Not Adequate");
                        }
                        break;
                    default:
                        System.out.println("------Thank You For Visiting-----------");
                        i = false;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Choose Options Carefully Numeric Digits Allowed Only");
        }
    }
}




