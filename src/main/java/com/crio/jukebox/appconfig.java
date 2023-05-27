package com.crio.jukebox;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.Command.JukeboxSeriveCommand;
import com.crio.jukebox.Services.JukeBoxService;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;

public class appconfig {
private UserRepository userRepository;
private SongRepository songRepository;
private PlaylistRepository playlistRepository;
public void executeCommand(String command, String[] args) {
    JukeBoxService jukeBoxService=new JukeBoxService(userRepository, songRepository, playlistRepository);
    JukeboxSeriveCommand commands=new JukeboxSeriveCommand(jukeBoxService);
    commands.executeCommand(command,args);
}
}
