package com.crio.jukebox.Command;
import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.Services.JukeBoxService;

public class JukeboxSeriveCommand {
    private JukeBoxService jukeBoxService;
    
    public JukeboxSeriveCommand(JukeBoxService jukeBoxService) {
        this.jukeBoxService = jukeBoxService;
    }

    public void executeCommand(String command, String[] args) {
        switch (command) {
            case "LOAD-DATA":
                if (args.length == 1) {
                    jukeBoxService.loadSongs(args[0]);
                } else {
                    System.out.println("Invalid arguments for LOAD command.");
                }
                break;
            case "CREATE-USER":
                if (args.length == 1) {
                    jukeBoxService.createUser(args[0]);
                } else {
                    System.out.println("Invalid arguments for CREATE-USER command.");
                }
                break;
            case "CREATE-PLAYLIST":
                if (args.length >= 3) {
                    int userId = Integer.parseInt(args[0]);
                    String playlistName = args[1];
                    List<Integer> songIds = new ArrayList<>();
                    for (int i = 2; i < args.length; i++) {
                        songIds.add(Integer.parseInt(args[i]));
                    }
                    jukeBoxService.createPlaylist(userId, playlistName, songIds);
                } else {
                    System.out.println("Invalid arguments for CREATE-PLAYLIST command.");
                }
                break;
            case "DELETE-PLAYLIST":
                if (args.length == 2) {
                    int userId = Integer.parseInt(args[0]);
                    int playlistId = Integer.parseInt(args[1]);
                    jukeBoxService.deletePlaylist(userId, playlistId);
                } else {
                    System.out.println("Invalid arguments for DELETE-PLAYLIST command.");
                }
                break;
            case "MODIFY-PLAYLIST":
                if (args.length >= 4) {
                    String action = args[0];
                    int userId = Integer.parseInt(args[1]);
                    int playlistId = Integer.parseInt(args[2]);
                    List<Integer> songIds = new ArrayList<>();
                    for (int i = 3; i < args.length; i++) {
                        songIds.add(Integer.parseInt(args[i]));
                    }
                    jukeBoxService.modifyPlaylist(action, userId, playlistId, songIds);
                } else {
                    System.out.println("Invalid arguments for MODIFY-PLAYLIST command.");
                }
                break;
            case "PLAY-PLAYLIST":
                if (args.length == 2) {
                    int userId = Integer.parseInt(args[0]);
                    int playlistId = Integer.parseInt(args[1]);
                    jukeBoxService.playPlaylist(userId, playlistId);
                } else {
                    System.out.println("Invalid arguments for PLAY-PLAYLIST command.");
                }
                break;
            case "PLAY-SONG":
                if (args.length == 2) {
                    // String action = args[0];
                    int userId = Integer.parseInt(args[0]);
                    String songId = args[1];
                    jukeBoxService.playSong(userId, songId);
                } else {
                    System.out.println("Invalid arguments for PLAY-SONG command.");
                }
                break;
        }
    }
}
