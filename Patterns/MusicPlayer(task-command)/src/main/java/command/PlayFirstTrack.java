package command;

public class PlayFirstTrack implements MusicPlayerCommand{

    private MusicPlayer musicPlayer;

    public PlayFirstTrack(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public void play() {
        musicPlayer.playFirstTrack();
    }
}
