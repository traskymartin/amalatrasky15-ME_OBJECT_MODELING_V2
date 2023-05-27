package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.crio.jukebox.entities.Song;

public class SongRepository {
    private static Map<Integer, Song> songs;

    public SongRepository() {
        songs = new HashMap<>();
    }

    public static void addSong(Song song) {
        songs.put(song.getSongNo(), song);
    }

    public Song getSong(int songId) {
        return songs.get(songId);
    }

    public List<Song> getsong() {
        List<Song> s=new ArrayList<>();
        for(Entry<Integer, Song> entry:songs.entrySet()){
            s.add(entry.getValue());
        }
        return s;
    }

    public static void addSong(List<Song> loadedSong) {
        Map<Integer,Song> s=new HashMap<>();
        for(Song ss:loadedSong){
            s.put(ss.getSongNo(), ss);
        }
        songs=s;
    }
}
