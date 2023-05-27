package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class PlayList{
    private static int id;
    private String playlistName;
    private List<Song> songs;
    public PlayList(){
        songs=new ArrayList();
    }
    public PlayList(int id, String playlistName, List<Song> songs) {
        this.id = id;
        this.playlistName = playlistName;
        this.songs = songs;
    }

    public PlayList(String playlistName) {
        this.id ++; // Assign a default negative value for id
        this.playlistName = playlistName;
        this.songs = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        List<Song> s=new ArrayList<>();
        boolean found = false;
        for (Song ps : songs) {
            s.add(ps);
            if (ps.getSongNo().equals(song.getSongNo())) {
                found = true;
                break;
            }
        }
        if (!found) {
           s.add(song);
        }
        songs=s;
    }

    public void removeSong(Song song) {
        List<Song> s=new ArrayList<>();
        for(Song ps:songs){
            s.add(ps);
        }
        s.removeIf(f -> f.getSongNo().equals(song.getSongNo()));
        songs=s;
    }

    @Override
    public String toString() {
        return "Playlist{id=" + id + ", playlistName='" + playlistName + "', songs=" + songs + "}";
    }

}
