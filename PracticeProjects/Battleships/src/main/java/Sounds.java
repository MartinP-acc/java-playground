import javafx.scene.media.AudioClip;

public class Sounds {

    private static Sounds instance;
    private final AudioClip hit;
    private final AudioClip miss;
    private final AudioClip sunk;
    private final AudioClip menu;

    private boolean enable;
    private double volume;


    private Sounds(){
        hit = new AudioClip("file:src/main/resources/sounds/hit_sound.mp3");
        miss = new AudioClip("file:src/main/resources/sounds/miss_sound.wav");
        sunk = new AudioClip("file:src/main/resources/sounds/sinking_sound.wav");
        menu = new AudioClip("file:src/main/resources/sounds/switch.wav");
        enable = true;
        volume = 0.7;
    }

    public static void initSounds(){
        if (instance == null) instance = new Sounds();
    }

    public static Sounds getInstance(){
        return instance;
    }

    public void playHit() {
        if (enable) hit.play(volume);
    }

    public void playMiss() {
        if (enable) miss.play(volume);
    }

    public void playSunk() {
        if (enable) sunk.play(volume);
    }

    public void playMenu() {
        if (enable) menu.play(volume);
    }

    public void setEnable(boolean enable){
        this.enable = enable;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }
}
