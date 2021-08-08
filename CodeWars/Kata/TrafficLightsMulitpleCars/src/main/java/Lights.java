public class Lights {
    private char light;
    private int timer;
    private final int position;

    public Lights(char light, int position) {
        this.light = light;
        this.timer = light == 'G' ? 1 : 7;
        this.position = position;
    }

    public void incTimer() {
        this.timer++;
        if (timer > 11) timer = 1;
        switchLight();
    }

    private void switchLight() {
        switch (timer) {
            case 1:
                light = 'G';
                break;
            case 6:
                light = 'O';
                break;
            case 7:
                light = 'R';
        }
    }

    public char getLight() {
        return light;
    }

    public int getPosition() {
        return position;
    }
}
