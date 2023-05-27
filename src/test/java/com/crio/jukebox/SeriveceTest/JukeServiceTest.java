package com.crio.jukebox.SeriveceTest;

import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.security.Provider.Service;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.Services.JukeBoxService;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;


public class JukeServiceTest {
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
public void loadSong(){
    JukeBoxService songLoader = new JukeBoxService(userRepository, songRepository, playlistRepository);

    // Assuming the test CSV file contains two songs
    String csvFile = "/home/crio-user/workspace/amalatrasky15-ME_OBJECT_MODELING_V2/songs.csv";
    songLoader.loadSongs(csvFile);

    // Retrieve the loaded songs from the Song repository
    List<Song> loadedSongs = SongRepository.getsong();

    // Check the number of loaded songs
    assertEquals(30, loadedSongs.size());
}
@Test
public void testCreateUser() {
    // Arrange
    String userName = "JohnDoe";
    User user=new User(userName);
    // Act
    userRepository.addUser(user);
    // Assert
    assertEquals(userRepository.getUser(1), user);
    assertEquals(1, userRepository.getUserCount(), 1);
}

@Test
public void testCreatePlaylist() {
    // Arrange
    User user = new User("JohnDoe");
    userRepository.addUser(user);
    JukeBoxService jukeBoxService=new JukeBoxService(userRepository, songRepository, playlistRepository);
    jukeBoxService.loadSongs("/home/crio-user/workspace/amalatrasky15-ME_OBJECT_MODELING_V2/songs.csv");
    List<Integer> songIds = List.of(1, 2);
    String playlistName = "My Playlist";
    jukeBoxService.createPlaylist(user.getId(), playlistName, songIds);
    // Assert
    assertEquals(1, playlistRepository.getPlaylistCount());
    PlayList playlist = playlistRepository.getPlaylist(1);
    assertNotNull(playlist);
    assertEquals(playlistName, playlist.getPlaylistName());
    assertEquals(2, playlist.getSongs().size());
}

@Test
    public void testDeletePlaylist() {
        // Arrange
        User user = new User("JohnDoe");
        userRepository.addUser(user);
        PlayList playlist = new PlayList("My Playlist");
        playlistRepository.addPlaylist(playlist);
        int userId = user.getId();
        int playlistId = playlist.getId();
        JukeBoxService jukeBoxService=new JukeBoxService(userRepository, songRepository, playlistRepository);
        // Act
        jukeBoxService.deletePlaylist(userId, playlistId);

        // Assert
        assertNull(playlistRepository.getPlaylist(playlistId));
    }
    @Test
    public void testModifyPlaylistAddSong() {
        // Arrange
        User user = new User("JohnDoe");
        userRepository.addUser(user);
        PlayList playlist = new PlayList("My Playlist");
        playlistRepository.addPlaylist(playlist);
        JukeBoxService jukeBoxService=new JukeBoxService(userRepository, songRepository, playlistRepository);
        jukeBoxService.loadSongs("/home/crio-user/workspace/amalatrasky15-ME_OBJECT_MODELING_V2/songs.csv");
        int userId = user.getId();
        int playlistId = playlist.getId();
        List<Integer> songIds = Arrays.asList(1,2);
        
        jukeBoxService.modifyPlaylist("ADD-SONG", userId, playlistId, songIds);

        // Assert
        assertTrue(playlist.getSongs().contains(songRepository.getSong(1)));
        assertTrue(playlist.getSongs().contains(songRepository.getSong(2)));
    }

    @Test
    public void testModifyPlaylistDeleteSong() {
        // Arrange
        User user = new User("JohnDoe");
        userRepository.addUser(user);
        PlayList playlist = new PlayList("My Playlist");
        playlistRepository.addPlaylist(playlist);
        JukeBoxService jukeBoxService=new JukeBoxService(userRepository, songRepository, playlistRepository);
        jukeBoxService.loadSongs("/home/crio-user/workspace/amalatrasky15-ME_OBJECT_MODELING_V2/songs.csv");
        playlist.addSong(songRepository.getSong(1));
        playlist.addSong(songRepository.getSong(2));
        int userId = user.getId();
        int playlistId = playlist.getId();
        List<Integer> songIds = Arrays.asList(1, 2);
        // Act
        jukeBoxService.modifyPlaylist("DELETE-SONG", userId, playlistId, songIds);

        // Assert
        assertFalse(playlist.getSongs().contains(songRepository.getSong(1)));
        assertFalse(playlist.getSongs().contains(songRepository.getSong(2)));
    }
    @Test
    public void testPlayPlaylistWithValidUserAndPlaylist() {
        // Arrange
        User user = new User("JohnDoe");
        userRepository.addUser(user);
        PlayList playlist = new PlayList("My Playlist");
        playlistRepository.addPlaylist(playlist);
        JukeBoxService jukeBoxService=new JukeBoxService(userRepository, songRepository, playlistRepository);
        jukeBoxService.loadSongs("/home/crio-user/workspace/amalatrasky15-ME_OBJECT_MODELING_V2/songs.csv");
        playlist.addSong(songRepository.getSong(1));
        playlist.addSong(songRepository.getSong(2));
        int userId = user.getId();
        int playlistId = playlist.getId();
        // Act
        jukeBoxService.playPlaylist(userId, playlistId);

        // Assert
        // You can add more specific assertions based on your implementation
        // In this example, we're asserting that the method completes without any exceptions.
        assertTrue(true, "playPlaylist method executed without errors");
    }

    @Test
    public void testPlaySongWithValidUserAndActivePlaylist() {
        // Arrange
        User user = new User("JohnDoe");
        userRepository.addUser(user);
        PlayList playlist = new PlayList("My Playlist");
        playlistRepository.addPlaylist(playlist);
        JukeBoxService jukeBoxService=new JukeBoxService(userRepository, songRepository, playlistRepository);
        jukeBoxService.loadSongs("/home/crio-user/workspace/amalatrasky15-ME_OBJECT_MODELING_V2/songs.csv");
        playlist.addSong(songRepository.getSong(1));
        playlist.addSong(songRepository.getSong(2));
        int userId = user.getId();
        int songId = songRepository.getSong(1).getSongNo();
    
        // Act
        jukeBoxService.playSong("PLAY", userId, songId);

        // Assert
        // You can add more specific assertions based on your implementation
        // In this example, we're asserting that the method completes without any exceptions.
        assertTrue(true, "playSong method executed without errors");
    }


}
