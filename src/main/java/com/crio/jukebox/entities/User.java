package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User{
    private String current;
    private int id=0;
    private String userName;
    private PlayList currentPlaylist;
    private List<PlayList> playlists;

    public User(String userName) {
        this.id++; // Assign a default negative value for id
        this.userName = userName;
        this.playlists = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
    public List<PlayList> getPlaylists() {
        return playlists;
    }

    public void addPlayList(PlayList playlist) {
        playlists.add(playlist);
    }

    public void removePlayList(int playlistId) {
        playlists.removeIf(p -> p.getId() == playlistId);
    }

    public void playPlaylist(int playlistId) {
        for(PlayList pl:playlists){
            if(pl.getId()==playlistId){
                currentPlaylist=pl;
            }
        }
       /*  currentPlaylist = playlists.stream()
                .filter(p -> p.getId() == playlistId)
                .findFirst()
                .orElse(null); */
        if (currentPlaylist != null && !currentPlaylist.getSongs().isEmpty()) {
            current = currentPlaylist.getSongs().get(0).toString();
        }
    }
    
    public String getSong() {
        return current;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", userName='" + userName + "', playlists=" + playlists + "}";
    }
}
