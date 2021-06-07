package command;

public class PlayRandomTrack implements MusicPlayerCommand{

    private MusicPlayer musicPlayer;

    public PlayRandomTrack(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public void play() {
        musicPlayer.playRandomTrack();
    }
}
