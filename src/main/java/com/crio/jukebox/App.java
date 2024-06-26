package com.crio.jukebox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.Command.JukeboxSeriveCommand;
import com.crio.jukebox.Services.JukeBoxService;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;


public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT-FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
	}

    public static void run(List<String> commandLineArgs) {
    // Complete the final logic to run the complete program.


    String inputFile = commandLineArgs.get(0).split("=")[1];
    if (commandLineArgs.size() != 1) {
        System.out.println("Please provide the input file path.");
        return;
    }
    try {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        JukeBoxService jukeBoxService = new JukeBoxService(
                new UserRepository(),
                new SongRepository(),
                new PlaylistRepository()
        );
        JukeboxSeriveCommand commands = new JukeboxSeriveCommand(jukeBoxService);

        while ((line = reader.readLine()) != null) {
            // Process each line/command from the input file
            String[] parts = line.split(" ");
            String command = parts[0];
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);
            // Call the executeCommand method from the Commands class
            commands.executeCommand(command, args);
        }
        reader.close();
    } catch (IOException e) {
        System.out.println("Error reading the input file: " + e.getMessage());
    }

    }
}
