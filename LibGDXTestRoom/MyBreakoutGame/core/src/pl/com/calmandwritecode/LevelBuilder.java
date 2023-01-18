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
            "b3b3b3b3b3b3b3b3b3b3  b3n"+
            "b4                    b4n"+
            "b5  b5b5b5b5b5b5b5b5b5b5n"+
            "b6                    b6n"+
            "b7b7b7b7b7b7b7b7b7b7  b7n"+
            "b0b0b0b0b0b0b0b0b0b0b0b0";

    public final static String LEVEL3 =
                    "b1b1n"+
                    "b2b2b2b2n"+
                    "b3b3b3b3b3b3n"+
                    "b4b4b4b4b4b4b4b4n"+
                    "b5b5b5b5b5b5b5b5b5b5n"+
                    "b6b6b6b6b6b6b6b6b6b6b6n"+
                    "b7b7b7b7b7b7b7b7b7b7b7b7n"+
                    "b0b0b0b0b0b0b0b0b0b0b0b0";

    private final static char BRICK = 'b';
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
                new Texture(Gdx.files.internal("brick8.png"))
        };
    }

    public Array<Brick> buildFromString(String map){
        Array<Brick> bricks = new Array<>();
        float x=30;
        float y=650;
        for (int i=0; i<map.length(); i++){
            char c = map.charAt(i);
            if (c == BRICK){
                i++;
                c = map.charAt(i);
                int index = Integer.parseInt(c+"");
                bricks.add(new Brick(x,y,textures[index]));
                x+=100;
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
