package com.crio.jukebox.repositories;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;

public class PlaylistRepository {
    private Map<Integer, PlayList> playlists;

    public PlaylistRepository() {
        playlists = new HashMap<>();
    }
    public List<Integer> getPlaylistids(){
        List<Integer> pl=new ArrayList<>();
        for(Map.Entry<Integer, PlayList> e:playlists.entrySet()){
            pl.add(e.getKey());
        }
        return pl;
    }
    public int getPlaylistId(PlayList playList){
        int ans=0;
        for(Map.Entry<Integer, PlayList> e:playlists.entrySet()){
            if(e.getValue()==playList){
                ans=e.getKey();
            }
        }return ans;
    }
    public void addPlaylist(PlayList playlist) {
        playlists.put(playlist.getId(), playlist);
    }

    public PlayList getPlaylist(int playlistId) {
        return playlists.get(playlistId);
    }

    public void deletePlaylist(int playlistId) {
        playlists.remove(playlistId);
    }

    public void modifyPlaylistDeleteSong(int playlistId, Song song) {
        PlayList playlist = playlists.get(playlistId);
        if (playlist != null) {
            Song songToRemove = null;
            for (Song songs : playlist.getSongs()) {
                if (songs.getSongNo() == song.getSongNo()) {
                    songToRemove = songs;
                    break;
                }
            }
            if (songToRemove != null) {
                
                playlist.removeSong(song);
            }
        }
    }
    
    public void modifyPlaylistAddSong(int playlistId, Song song) {
        PlayList playlist = playlists.get(playlistId);
        if (playlist != null) {
            boolean songExists = playlist.getSongs().stream()
                    .anyMatch(s -> s.getSongNo() == song.getSongNo());
            if (!songExists) {
                playlist.addSong(song);
            }
        }
    }

    public Integer getPlaylistCount() {
        return playlists.size();
    }
    
}
