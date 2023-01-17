package pl.com.calmandwritecode;

public class Center {

    float x,y;

    public Center(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distanceTo(Center other){
        float a = x- other.x;
        float b = y- other.y;
        return a*a + b+b;
    }
}
