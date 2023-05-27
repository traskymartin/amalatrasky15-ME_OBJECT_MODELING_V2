package com.crio.jukebox.entities;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("Song")
public class SongTest {
    @Test
    @DisplayName("Song in PlayList")
    public void song_loading(){
        String name = "Crio.Do PhonePe TechScholars Assessment #1";
        List<String> artistGroup=new ArrayList<>();
        artistGroup.add("Ed Sheeran");
        artistGroup.add("Cardi");
        artistGroup.add("Camilla.B");
        artistGroup.add("Camilla Cabello");
        Song song =  new Song(1, "South of the Border", "Pop","Ed Sheeran",artistGroup,"No.6 Collaborations Project" );
        //Act and Assert
        String str=song.toString();
        PlayList playList=new PlayList("trasku");
        playList.addSong(song);
        Assertions.assertEquals(str,song.toString());
    }
}
