package videos;

public class Channel {

    private String channelName;
    private int subscriptions;
    private int number_of_videos;

    public Channel(String channel, int subscriptions, int number_of_videos) {
        this.channelName = channel;
        this.subscriptions = subscriptions;
        this.number_of_videos = number_of_videos;
    }

    public String getChannelName() {
        return channelName;
    }

    public int getSubscriptions() {
        return subscriptions;
    }

    public int getNumber_of_videos() {
        return number_of_videos;
    }
}
