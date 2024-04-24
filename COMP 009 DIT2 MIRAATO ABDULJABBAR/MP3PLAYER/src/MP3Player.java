import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

interface Playable {
    void load(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException;
    void play();
    void stop();
    void reset();
    void close();
}

class MusicPlayer implements Playable {
    private Clip clip;
    private String trackName;

    public MusicPlayer(String trackName) {
        this.trackName = trackName;
    }

    @Override
    public void load(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File(fileName);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    @Override
    public void play() {
        clip.start();
        System.out.println("Playing: " + trackName);
    }

    @Override
    public void stop() {
        clip.stop();
        System.out.println("Stopped: " + trackName);
    }

    @Override
    public void reset() {
        clip.setMicrosecondPosition(0);
        System.out.println("Reset: " + trackName);
        play();
    }

    @Override
    public void close() {
        clip.close();
        System.out.println("Closed: " + trackName);
    }

    public String getTrackName() {
        return trackName;
    }
}

public class MP3Player {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Scanner scanner = new Scanner(System.in);
        Playable[] tracks = {
            new MusicPlayer("Track 1"),
            new MusicPlayer("Track 2"),
            new MusicPlayer("Track 3")
        };

        int choice;

        System.out.println("Select a track to play:");
        for (int i = 0; i < tracks.length; i++) {
            System.out.println((i + 1) + ". " + ((MusicPlayer) tracks[i]).getTrackName());
        }
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        choice--;

        String[] fileNames = {"90x27s-rampb-guitar-loop-171092.mp3", "run-staccato-string-loop-167bpm-131104.mp3", "timbo-drumline-loop-103bpm-171091.mp3"};
        tracks[choice].load(fileNames[choice]);

        String response = "";

        try {
            tracks[choice].play();
            while (!response.equals("Q")) {
                System.out.println("P = play, S = Stop, R = Reset, Q = Quit, M = Go back to track selection");
                System.out.print("Enter your choice: ");
                response = scanner.next().toUpperCase();

                switch (response) {
                    case "P":
                        tracks[choice].play();
                        break;
                    case "S":
                        tracks[choice].stop();
                        break;
                    case "R":
                        tracks[choice].reset();
                        break;
                    case "Q":
                        tracks[choice].close();
                        break;
                    case "M":
                    	MP3Player.main(args);
                    	break;
                    default:
                        System.out.println("Please input a valid response");
                }
            }
        } finally {
            scanner.close();
        }
        System.out.println("Thank you for listening!!");
    }
}
