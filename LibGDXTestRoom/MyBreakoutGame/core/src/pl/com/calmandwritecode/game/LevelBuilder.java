package pl.com.calmandwritecode.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class LevelBuilder {

    private final static char BRICK = 'b';
    private final static char HARD_BRICK = 'h';
    private final static char WALL_BRICK = 'w';
    private final static char EMPTY = ' ';
    private final static char NEXT_LINE = 'n';

    private final Sprite[] textures;

    public LevelBuilder(TextureAtlas atlas){
        textures = new Sprite[]{
                atlas.createSprite("brick1"),
                atlas.createSprite("brick2"),
                atlas.createSprite("brick3"),
                atlas.createSprite("brick4"),
                atlas.createSprite("brick5"),
                atlas.createSprite("brick6"),
                atlas.createSprite("brick7"),
                atlas.createSprite("brick8"),
                atlas.createSprite("wall_brick"),
                atlas.createSprite("hard_brick0"),
                atlas.createSprite("hard_brick1"),
                atlas.createSprite("hard_brick2"),
        };
    }


    public Array<Brick> buildFromString(String map){
        Array<Brick> bricks = new Array<>();
        float x=15;
        float y=670;
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
                bricks.add(new HardBrick(x, y, textures[9],textures[10],textures[11]));
                x += 100;
            }else if (c == WALL_BRICK){
                i++;
                bricks.add(new WallBrick(x, y, textures[8]));
                x += 100;
            }else if (c == EMPTY){
                x+=50;
            }else if (c == NEXT_LINE){
                y-=30;
                x=15;
            }
        }
        return bricks;
    }
}
