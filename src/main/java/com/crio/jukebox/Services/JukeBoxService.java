package com.crio.jukebox.Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;

public class JukeBoxService {
    private UserRepository userRepository;
    private SongRepository songRepository;
    private PlaylistRepository playlistRepository;
    
    private List<Song> loadedSong=new ArrayList<>();
    
    public JukeBoxService(UserRepository userRepository, SongRepository songRepository, PlaylistRepository playlistRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }
    public void loadSongs(String csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] songData = line.split(",");
                if (songData.length >= 6) {
                    int songNo=Integer.parseInt(songData[0]);
                    String songName = songData[1];
                    String genre = songData[2];
                    String albumName = songData[3];
                    String albumArtist = songData[4];
                    String[] featuredArtists = songData[5].split("#");
                    List<String> artistgrp=new ArrayList<>();
                    for(String s:featuredArtists){
                        artistgrp.add(s);
                    }
                    Song song = new Song(songNo,songName, genre,albumArtist,artistgrp, albumName);
                    loadedSong.add(song);
                }
            }
            SongRepository.addSong(loadedSong);
            System.out.println("Songs Loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createUser(String userName) {
        User user = new User(userName);
        userRepository.addUser(user);
        System.out.println(user.getId()+" "+user.getUserName());
    }
    public void createPlaylist(int userId, String playlistName, List<Integer> songIds) {
        User user = userRepository.getUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        List<Song> songs = new ArrayList<>();
        for (int songId : songIds) {
            Song song = songRepository.getSong(songId);
            if (song != null) {
                songs.add(song);
            }
        }
        PlayList pl=new PlayList(playlistName);
        PlayList playlist = new PlayList(pl.getId(), playlistName, songs);
        playlistRepository.addPlaylist(playlist);
        System.out.println("Playlist ID - "+playlist.getId());
    }
    public void deletePlaylist(int userId, int playlistId) {
        User user = userRepository.getUser(userId);
        if (user == null) {
            return;
        }
        
        PlayList playlist = playlistRepository.getPlaylist(playlistId);
        if (playlist == null) {
            return;
        }
        
        playlistRepository.deletePlaylist(playlistId);
        System.out.println("Delete Successful");;
    }

    public void modifyPlaylist(String action, int userId, int playlistId, List<Integer> songIds) {
        User user = userRepository.getUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        PlayList playlist = playlistRepository.getPlaylist(playlistId);
        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }
        List<Integer> existingSongIds=new ArrayList<>();
        for(Song song:playlist.getSongs()){
            existingSongIds.add(song.getSongNo());
        }        
        if (action.equals("ADD-SONG")) {
            for (int songId : songIds) {
                if (!existingSongIds.contains(songId)) {
                    Song song = songRepository.getSong(songId);
                    if (song == null) {
                        return;
                    }
                    playlist.addSong(song);
                }
            }
            System.out.println("Playlist ID - "+playlistRepository.getPlaylistId(playlist));
            System.out.println("Playlist Name - "+playlist.getPlaylistName());
            System.out.print("Song IDs - ");
            List<Song> ss=playlist.getSongs();
            for(int i=0;i<ss.size();i++ ){
                if(i==ss.size()-1){
                    System.out.print(ss.get(i).getSongNo());
                }else{
                    System.out.print(ss.get(i).getSongNo()+" ");
                }
            }
            System.out.println();
        } else if (action.equals("DELETE-SONG")) {
            List<Song> tempsong=new ArrayList<>();

            for(Integer i:songIds){
                tempsong.add(songRepository.getSong(i));
            }
            for(Song song:tempsong){
                playlist.removeSong(song);
            }
            System.out.println("Playlist ID - "+playlistRepository.getPlaylistId(playlist));
            System.out.println("Playlist Name - "+playlist.getPlaylistName());
            System.out.print("Song IDs - ");
            List<Song> ss=playlist.getSongs();
            for(int i=0;i<ss.size();i++ ){
                if(i==ss.size()-1){
                    System.out.print(ss.get(i).getSongNo());
                }else{
                    System.out.print(ss.get(i).getSongNo()+" ");
                }
            }
            // for(Song song:playlist.getSongs()){
            //     System.out.print(song.getSongNo()+" ");
            // }
            System.out.println();
        } 
    }
    public void playPlaylist(int userId, int playlistId) {
        User user = userRepository.getUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        PlayList playlist = playlistRepository.getPlaylist(playlistId);
        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }
        
        List<Integer> songIds =new ArrayList<>();
        for(Song s: playlist.getSongs()){
            songIds.add(s.getSongNo());
        }
        if (songIds.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }
        
        int firstSongId = songIds.get(0);
        Song firstSong = songRepository.getSong(firstSongId);
        if (firstSong == null) {
            System.out.println("Song not found.");
            return;
        }
        
        System.out.println("Current Song Playing");
        System.out.println("Song - " + firstSong.getSongName());
        System.out.println("Album - " + firstSong.getAlbum());  
        System.out.print("Artists - ");
        List<String> art=firstSong.getArtistGroup();
        for(int i=0;i<art.size();i++){
            if(i==art.size()-1){
                System.out.print(art.get(i));
            }else{
                System.out.print(art.get(i)+",");
            }
        }
        // for(String s:firstSong.getArtistGroup()){
        //     System.out.print(s+",");
        // }
        System.out.println();
    }
    private int songId=-1; ;
    private  String action="" ;
    int currentnext=-1;
    public void playSong(int userId, String songobj) {
       
        if (songobj.equals("NEXT") || songobj.equals("BACK")) {
            action = songobj;
        } else {
            action="";
            songId = Integer.parseInt(songobj);
        }
    
        User user = userRepository.getUser(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        PlayList activePlaylist;
        List<Integer> playlistids=playlistRepository.getPlaylistids();
        if(playlistids!=null||playlistids.size()!=0){
            activePlaylist=playlistRepository.getPlaylist(playlistids.get(0));
        }
        else{
            System.out.println("No active playlist found.");
            return;
        }
    
        List<Integer> songIds = new ArrayList<>();
        for (Song s1 : activePlaylist.getSongs()) {
            songIds.add(s1.getSongNo());
        }
        if (!songIds.contains(songId) && action.isEmpty()) {
            System.out.println("Given song id is not a part of the active playlist");
            return;
        }
        int currentIndex = songIds.indexOf(songId);
        int nextIndex = (currentIndex + 1) % songIds.size();
        int prevIndex = (currentIndex - 1 + songIds.size()) % songIds.size();
        if (action.equals("NEXT")) {
            Song nextSong = songRepository.getSong(songIds.get(nextIndex));
            if (nextSong == null) {
                System.out.println("Song not found.");
                return;
            }
            currentnext=nextIndex;
            System.out.println("Current Song Playing");
            System.out.println("Song - " + nextSong.getSongName());
            System.out.println("Album - " + nextSong.getAlbum());
            System.out.print("Artists - ");
            List<String> art=nextSong.getArtistGroup();
            for(int i=0;i<art.size();i++){
                if(i==art.size()-1){
                    System.out.print(art.get(i));
                }else{
                    System.out.print(art.get(i)+",");
                }
            }
            System.out.println();
            songId=nextSong.getSongNo();
        } else if (action.equals("BACK")) {
            Song prevSong = songRepository.getSong(songIds.get(prevIndex));
            if (prevSong == null) {
                System.out.println("Song not found.");
                return;
            }
            System.out.println("Current Song Playing");
            System.out.println("Song - " + prevSong.getSongName());
            System.out.println("Album - " + prevSong.getAlbum());
            System.out.print("Artists - ");
            List<String> art=prevSong.getArtistGroup();
            for(int i=0;i<art.size();i++){
                if(i==art.size()-1){
                    System.out.print(art.get(i));
                }else{
                    System.out.print(art.get(i)+",");
                }
            }
            System.out.println();
            songId=prevSong.getSongNo();
        } else {
            Song selectedSong = songRepository.getSong(songId);
            if (selectedSong == null) {
                System.out.println("Song not found.");
                return;
            }
            System.out.println("Current Song Playing");
            System.out.println("Song - " + selectedSong.getSongName());
            System.out.println("Album - " + selectedSong.getAlbum());
            System.out.print("Artists - ");
            List<String> art=selectedSong.getArtistGroup();
            for(int i=0;i<art.size();i++){
                if(i==art.size()-1){
                    System.out.print(art.get(i));
                }else{
                    System.out.print(art.get(i)+",");
                }
            }
            System.out.println();
        }
    }
    }
