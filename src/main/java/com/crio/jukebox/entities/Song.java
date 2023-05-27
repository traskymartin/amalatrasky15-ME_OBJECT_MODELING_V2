package com.crio.jukebox.entities;

import java.util.List;

public class Song{
    private Integer songNo;
    private String songName;
    private String genre;
    private String artist;
    private List<String> artistgroup;
    private String album;
    public Song(Song song){
        this(song.songNo,song.songName,song.genre,song.artist,song.artistgroup,song.album);
    }
    public Song(Integer songNo, String songName, String genre, String artist, List<String> artistgroup, String album) {
        this.songNo = songNo;
        this.songName = songName;
        this.genre = genre;
        this.artist = artist;
        this.artistgroup = artistgroup;
        this.album = album;
    }
    public String getSongName() {
        return songName;
    }

    public Integer getSongNo() {
        return songNo;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtist() {
        return artist;
    }

    public List<String> getArtistGroup() {
        return artistgroup;
    }

    public String getAlbum() {
        return album;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", genre='" + genre + '\'' +
                ", albumName='" + album + '\'' +
                ", albumArtist='" + artist + '\'' +
                ", featuredArtists=" + artistgroup +
                '}';
    }}
