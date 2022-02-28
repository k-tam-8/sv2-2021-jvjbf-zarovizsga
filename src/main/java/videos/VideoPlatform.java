package videos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {

    private List<Channel> channels = new ArrayList<>();

    public List<Channel> getChannels() {
        return new ArrayList<>(channels);
    }

    public void readDataFromFile(Path path) {
        List<String> readIn;
        try {
            readIn = Files.readAllLines(path);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot open file for read!");
        }
        addChannelsFromFile(readIn);
    }

    private void addChannelsFromFile(List<String> readIn){
        for (int i = 1; i < readIn.size(); i++) {
            String[] stringArr = readIn.get(i).split(";");
            channels.add(new Channel(stringArr[0], Integer.parseInt(stringArr[1]), Integer.parseInt(stringArr[2])));
        }
    }

    public int calculateSumOfVideos() {
        return channels.stream().mapToInt(Channel::getNumber_of_videos).sum();
    }
}
