package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class EditorScreen implements Screen {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Rectangle cursorRect;
    private GridCell[][] grid;
    private Stage stage;
    private ButtonGroup<ImageButton> buttons;


    @Override
    public void show() {

        cursorRect = new Rectangle(0,0,1,1);
        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        atlas = new TextureAtlas("breakout-tx.atlas");
        grid = new GridCell[16][25];

        for (int row = 0; row<16; row++){
            for (int col = 0; col<25; col++){
                grid[row][col]=new GridCell(row,col);
            }
        }

        ImageButton brick1Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick1")));
        ImageButton brick2Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick2")));
        ImageButton brick3Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick3")));
        ImageButton brick4Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick4")));
        ImageButton brick5Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick5")));
        ImageButton brick6Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick6")));
        ImageButton brick7Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick7")));
        ImageButton brick8Button = new ImageButton(new SpriteDrawable(atlas.createSprite("brick8")));
        ImageButton hardBrickButton = new ImageButton(new SpriteDrawable(atlas.createSprite("hard_brick0")));
        ImageButton wallBrickButton = new ImageButton(new SpriteDrawable(atlas.createSprite("wall_brick")));


        buttons = new ButtonGroup<>();
        buttons.add(brick1Button,
                brick2Button,
                brick3Button,
                brick4Button,
                brick5Button,
                brick6Button,
                brick7Button,
                brick8Button,
                hardBrickButton,
                wallBrickButton);

        buttons.setUncheckLast(true);

        Table root = new Table();
        root.add(brick1Button,
                brick2Button,
                brick3Button,
                brick4Button,
                brick5Button,
                brick6Button,
                brick7Button,
                brick8Button,
                hardBrickButton,
                wallBrickButton);

        root.setBounds(0,0,Gdx.graphics.getWidth(),150);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        stage.addActor(root);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cursorRect.setPosition(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int row = 0; row<16; row++){
            for (int col = 0; col<25; col++){
                GridCell cell = grid[row][col];
                cell.draw(shapeRenderer,cursorRect);
                if (col==0 || (col<24 && grid[row][col-1].sprite==null)) {
                    cell.setTexture(atlas, buttons);
                }else{
                    cell.sprite=null;
                }
            }
        }
        shapeRenderer.end();

        batch.begin();
        for (int row = 0; row<16; row++) {
            for (int col = 0; col < 25; col++) {
                GridCell cell = grid[row][col];
                cell.drawSprite(batch);
            }
        }
        batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        shapeRenderer.dispose();

    }
}
