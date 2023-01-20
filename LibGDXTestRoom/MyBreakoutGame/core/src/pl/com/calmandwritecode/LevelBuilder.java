package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class LevelBuilder {

    public final static String LEVEL1 = "b1b1b1b1b1b1b1b1b1b1b1b1n"+
                                        "b2b2b2b2b2b2b2b2b2b2b2b2n"+
                                        "b3b3b3b3b3b3b3b3b3b3b3b3n"+
                                        "b4b4b4b4b4b4b4b4b4b4b4b4n"+
                                        "b5b5b5b5b5b5b5b5b5b5b5b5n"+
                                        "b6b6b6b6b6b6b6b6b6b6b6b6n"+
                                        "b7b7b7b7b7b7b7b7b7b7b7b7n"+
                                        "b0b0b0b0b0b0b0b0b0b0b0b0";

    public final static String LEVEL2 =
            "b1b1b1b1b1b1b1b1b1b1b1b1n"+
            "b2                    b2n"+
            "b3            b3b3b3  b3n"+
            "b4                    b4n"+
            "b5  b5b5b5b5          b5n"+
            "b6                    b6n"+
            "b7b7b7b7b7b7b7b7b7b7  b7n"+
            "hbhbhbhbhbhbhbhbhbhb  hb";

    public final static String LEVEL3 =
                    "b1b1                  hbn"+
                    "b2b2b2b2              hbn"+
                    "b3b3b3b3b3b3          hbn"+
                    "b4b4b4b4b4b4b4b4      hbn"+
                    "b5b5b5b5b5b5b5b5b5b5  hbn"+
                    "b6b6b6b6b6b6b6b6b6b6b6n"+
                    "b7b7b7b7b7b7b7b7b7b7b7b7n"+
                    "b0b0b0b0b0b0b0b0b0b0b0b0n"+
                    "wbwbwbwbwbwbwbwbwbwbwb";

    public final static String LEVEL4 =
                    "b1b1n"+
                    "b2b2b2b2n"+
                    "b3b3b3b3b3b3        hbn"+
                    "b4b4b4b4b4b4b4b4    hbn"+
                    "b5b5b5b5b5b5b5b5b5b5hbn"+
                    "b6b6b6b6b6b6b6b6b6b6wbn"+
                    "b7b7b7b7b7b7b7b7b7b7wbn"+
                    "b0b0b0b0b0b0b0b0b0b0wbn"+
                    "wbwbwbwbwbwbwbwbwbwbwb";

    private final static char BRICK = 'b';
    private final static char HARD_BRICK = 'h';
    private final static char WALL_BRICK = 'w';
    private final static char EMPTY = ' ';
    private final static char NEXT_LINE = 'n';

    private final Texture[] textures;

    public LevelBuilder(){
        textures = new Texture[]{
                new Texture(Gdx.files.internal("brick1.png")),
                new Texture(Gdx.files.internal("brick2.png")),
                new Texture(Gdx.files.internal("brick3.png")),
                new Texture(Gdx.files.internal("brick4.png")),
                new Texture(Gdx.files.internal("brick5.png")),
                new Texture(Gdx.files.internal("brick6.png")),
                new Texture(Gdx.files.internal("brick7.png")),
                new Texture(Gdx.files.internal("brick8.png")),
                new Texture(Gdx.files.internal("wall_brick.png"))
        };
    }

    public Array<Brick> buildFromString(String map){
        Array<Brick> bricks = new Array<>();
        float x=30;
        float y=610;
        for (int i=0; i<map.length(); i++){
            char c = map.charAt(i);
            if (c == BRICK) {
                i++;
                c = map.charAt(i);
                int index = Integer.parseInt(c + "");
                bricks.add(new Brick(x, y, textures[index]));
                x += 100;
            }else if (c == HARD_BRICK){
                i++;
                bricks.add(new HardBrick(x, y, null));
                x += 100;
            }else if (c == WALL_BRICK){
                i++;
                bricks.add(new WallBrick(x, y, textures[8]));
                x += 100;
            }else if (c == EMPTY){
                x+=50;
            }else if (c == NEXT_LINE){
                y-=30;
                x=30;
            }
        }
        return bricks;
    }

    public void dispose(){
        for(Texture texture : textures) texture.dispose();
    }

}
