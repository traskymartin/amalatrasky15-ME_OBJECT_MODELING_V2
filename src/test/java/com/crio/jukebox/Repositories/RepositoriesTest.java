package com.crio.jukebox.Repositories;
import java.util.Arrays;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class RepositoriesTest {
    private UserRepository userRepository;
    private SongRepository songRepository;
    private PlaylistRepository playlistRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        songRepository = new SongRepository();
        playlistRepository = new PlaylistRepository();
    }

    @Test
    public void testAddUserAndGetUser() {
        User user = new User("Kiran");
        userRepository.addUser(user);

        User retrievedUser = userRepository.getUser(1);
        Assertions.assertEquals(user, retrievedUser);
    }

    @Test
    public void testAddSongAndGetSong() {
        Song song = new Song(1, "South of the Border", "Pop", 
                "Ed Sheeran", Arrays.asList("Ed Sheeran", "Cardi B", "Camilla Cabello"),"No.6 Collaborations Project");
        songRepository.addSong(song);

        Song retrievedSong = songRepository.getSong(1);
        Assertions.assertEquals(song, retrievedSong);
    }

    @Test
    public void testAddPlaylistAndGetPlaylist() {
        Song song = new Song(1, "South of the Border", "Pop", 
        "Ed Sheeran", Arrays.asList("Ed Sheeran", "Cardi B", "Camilla Cabello"),"No.6 Collaborations Project");
        PlayList playlist = new PlayList(1, "MY_PLAYLIST_1", Arrays.asList(song));
        playlistRepository.addPlaylist(playlist);

        PlayList retrievedPlaylist = playlistRepository.getPlaylist(1);
        Assertions.assertEquals(playlist, retrievedPlaylist);
    }

    @Test
    public void testDeletePlaylist() {
        Song song = new Song(1, "South of the Border", "Pop", 
        "Ed Sheeran", Arrays.asList("Ed Sheeran", "Cardi B", "Camilla Cabello"),"No.6 Collaborations Project");
        PlayList playlist = new PlayList(1, "MY_PLAYLIST_1", Arrays.asList(song));
        playlistRepository.addPlaylist(playlist);

        playlistRepository.deletePlaylist(1);

        PlayList retrievedPlaylist = playlistRepository.getPlaylist(1);
        Assertions.assertNull(retrievedPlaylist);
    }

    @Test
    public void testModifyPlaylistAddSong() {
        Song song1 = new Song(1, "Song 1", "Pop", "Artist 1", null, "Album 1");
        Song song2 = new Song(2, "Song 2", "Rock", "Artist 2", null, "Album 2");
        PlayList playlist = new PlayList(1, "MY_PLAYLIST_1", Arrays.asList(song1));
        playlistRepository.addPlaylist(playlist);
    
        playlistRepository.modifyPlaylistAddSong(1, song2);
    
        PlayList modifiedPlaylist = playlistRepository.getPlaylist(1);
        Assertions.assertTrue(modifiedPlaylist.getSongs().contains(song2));
    }

    @Test
    public void testModifyPlaylistDeleteSong() {
        Song song = new Song(1, "South of the Border", "Pop", 
        "Ed Sheeran", Arrays.asList("Ed Sheeran", "Cardi B", "Camilla Cabello"),"No.6 Collaborations Project");
        Song song2 = new Song(2, "South of the Border", "Pop", 
        "Ed Sheeran", Arrays.asList("Ed Sheeran", "Cardi B", "Camilla Cabello"),"No.6 Collaborations Project");
        PlayList playlist = new PlayList(1, "MY_PLAYLIST_1", Arrays.asList(song,song2));
        playlistRepository.addPlaylist(playlist);

        playlistRepository.modifyPlaylistDeleteSong(1, song2);

        PlayList modifiedPlaylist = playlistRepository.getPlaylist(1);
        Assertions.assertFalse(modifiedPlaylist.getSongs().contains(2));
    }  
}
