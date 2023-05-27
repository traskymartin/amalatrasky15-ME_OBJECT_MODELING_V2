package com.crio.jukebox.entities;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("User")
public class UserTest {
    String name = "Crio.Do PhonePe TechScholars Assessment #1";
     
        @Test
        @DisplayName("Create User")
        public void createUserTest() {
            String expected = "User{id=1, userName='trasky', playlists=[Playlist{id=1, playlistName='playlist1', songs=[]}]}";
            PlayList playlist = new PlayList("playlist1");
            User user = new User("trasky");
            user.addPlayList(playlist);
            String actual = user.toString();
            Assertions.assertEquals(expected, actual);
        }
    
        @Test
        @DisplayName("Play Playlist")
        public void playPlaylistTest() {
            PlayList playlist = new PlayList("playlist1");
            Song song1 = new Song(1, "Song 1", "Pop", "Artist 1", null, "Album 1");
            Song song2 = new Song(2, "Song 2", "Rock", "Artist 2", null, "Album 2");
            playlist.addSong(song1);
            playlist.addSong(song2);
    
            User user = new User("trasky");
            user.addPlayList(playlist);
            user.playPlaylist(1);
    
            String expected = "Song{songName='Song 1', genre='Pop', albumName='Album 1', albumArtist='Artist 1', featuredArtists=null}";
            String actual = user.getSong();
            Assertions.assertEquals(expected, actual);
        }
    
        @Test
        @DisplayName("Remove Playlist")
        public void removePlaylistTest() {
            PlayList playlist = new PlayList("playlist1");
            User user = new User("trasky");
            user.addPlayList(playlist);
    
            user.removePlayList(1);
            String expected = "User{id=1, userName='trasky', playlists=[]}";
            String actual = user.toString();
            Assertions.assertEquals(expected, actual);
        }
}
